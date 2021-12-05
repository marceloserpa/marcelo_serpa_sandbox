package com.marceloserpa.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.DistanceFeatureQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class QueriesPoc {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        System.out.println("============= Without boosting ===============");
        BoolQueryBuilder queryWithoutBoost = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "chocolate"));

        SearchRequest searchRequestWithoutBoost = new SearchRequest("products");
        searchRequestWithoutBoost.source(new SearchSourceBuilder().query(queryWithoutBoost));

        render(client.search(searchRequestWithoutBoost, RequestOptions.DEFAULT));

        System.out.println("\n\n============= Boost documents based on date ===============");
        BoolQueryBuilder queryWithBoostByDate = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "chocolate"))
                .should(QueryBuilders.distanceFeatureQuery("production_date", new DistanceFeatureQueryBuilder.Origin("now"), "7d"));

        SearchRequest searchRequestWithBoostByDate = new SearchRequest("products");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryWithBoostByDate);
        searchRequestWithBoostByDate.source(searchSourceBuilder);

        render(client.search(searchRequestWithBoostByDate, RequestOptions.DEFAULT));


        System.out.println("\n\n============= Boost documents based on location ===============");

        GeoPoint geo = new GeoPoint(41.15,-71.3);
        BoolQueryBuilder queryWithBoostByLocation = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", "chocolate"))
                .should(QueryBuilders.distanceFeatureQuery("location", new DistanceFeatureQueryBuilder.Origin(geo), "1000m"));

        SearchRequest searchRequestWithBoostByLocation = new SearchRequest("products");
        searchRequestWithBoostByLocation.source(new SearchSourceBuilder().query(queryWithBoostByLocation));

        render(client.search(searchRequestWithBoostByLocation, RequestOptions.DEFAULT));

        client.close();

    }

    private static void render(SearchResponse searchResponse) {
        for(SearchHit search : searchResponse.getHits().getHits()){
            float score = search.getScore();
            String id = search.getId();

            Map<String, Object> sourceAsMap = search.getSourceAsMap();
            String name = (String) sourceAsMap.get("name");
            String productionDate = (String) sourceAsMap.get("production_date");
            ArrayList location = (ArrayList) sourceAsMap.get("location");

            String text = String.format(">> id=%s, score=%s, name=%s, productionDate=%s, location=%s",
                    id, score, name, productionDate, location);
            System.out.println(text);
        }
    }


}
