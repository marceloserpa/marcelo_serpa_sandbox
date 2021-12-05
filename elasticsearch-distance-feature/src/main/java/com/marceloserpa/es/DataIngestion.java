package com.marceloserpa.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataIngestion {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        // Creating the products index

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));

        Map<String, Object> name = new HashMap<>();
        name.put("type", "keyword");

        Map<String, Object> productionDate = new HashMap<>();
        productionDate.put("type", "date");

        Map<String, Object> location = new HashMap<>();
        location.put("type", "geo_point");

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("production_date", productionDate);
        properties.put("location", location);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        createIndexRequest.mapping(mapping);

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        // Creating documents
        BulkRequest createProducts = new BulkRequest();
        createProducts.add(new IndexRequest("products").id("1")
                .source(createDocumentMap("chocolate", LocalDate.of(2018,2,1), new Double[]{-71.34, 41.12})));
        createProducts.add(new IndexRequest("products").id("2")
                .source(createDocumentMap("chocolate", LocalDate.of(2018, 1, 1), new Double[]{-71.3, 41.15})));
        createProducts.add(new IndexRequest("products").id("3")
                .source(createDocumentMap("chocolate", LocalDate.of(2017,12, 1), new Double[]{-71.3, 41.12})));
        createProducts.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        createProducts.setRefreshPolicy("wait_for");

        BulkResponse bulkResponse = client.bulk(createProducts, RequestOptions.DEFAULT);





        client.close();

    }

    public static Map<String, Object> createDocumentMap(String name, LocalDate productionDate, Double[] point){
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);

        Date date = Date.from(productionDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        jsonMap.put("production_date", date);
        jsonMap.put("location",point);
        return jsonMap;
    }


}
