package com.marceloserpa.poc;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationQueryPoC {


    public static void main(String[] args) throws IOException {


        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        System.out.println("=== Querying for Hacker News posts ===");
        SearchRequest searchRequestHackerNews = new SearchRequest();
        searchRequestHackerNews.indices("blog-article-2022-hackernews-alias");

        SearchSourceBuilder searchSourceBuilderHackerNews = new SearchSourceBuilder();
        searchSourceBuilderHackerNews.query(QueryBuilders.matchAllQuery());
        searchRequestHackerNews.source(searchSourceBuilderHackerNews);

        SearchResponse searchResponseHackerNews = client.search(searchRequestHackerNews, RequestOptions.DEFAULT);
        List<Post> postsHackerNews = mapToPosts(searchResponseHackerNews);
        postsHackerNews.forEach(System.out::println);


        System.out.println("=== Querying for InfoQ posts ===");
        SearchRequest searchRequestInfoQ = new SearchRequest();
        searchRequestInfoQ.indices("blog-article-2022-infoq-alias");

        SearchSourceBuilder searchSourceBuilderInfoQ = new SearchSourceBuilder();
        searchSourceBuilderInfoQ.query(QueryBuilders.matchAllQuery());
        searchRequestInfoQ.source(searchSourceBuilderInfoQ);

        SearchResponse searchResponseInfoQ = client.search(searchRequestInfoQ, RequestOptions.DEFAULT);
        List<Post> postsInfoQ = mapToPosts(searchResponseInfoQ);
        postsInfoQ.forEach(System.out::println);

        client.close();

    }

    private static List<Post> mapToPosts(SearchResponse searchResponse) {
        List<Post> posts = new ArrayList<>();
        for (SearchHit search : searchResponse.getHits().getHits()) {
            Post post = new Post();
            post.setId(search.getId());

            Map<String, Object> sourceAsMap = search.getSourceAsMap();
            post.setTenant((String) sourceAsMap.get("tenant"));
            post.setTitle((String) sourceAsMap.get("title"));
            post.setSlug((String) sourceAsMap.get("slug"));
            post.setContent((String) sourceAsMap.get("content"));

            String dateString = (String) sourceAsMap.get("create_at");
            post.setCreateAt(LocalDate.parse(dateString, ApplicationIngestion.FORMATTER));

            posts.add(post);
        }

        return posts;
    }


}
