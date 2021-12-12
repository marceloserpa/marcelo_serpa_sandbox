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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataIngestionNonSortedIndex {


    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));


        // Creating the products index
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("posts_not_sorted");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 50)
                .put("index.number_of_replicas", 2));

        Map<String, Object> name = new HashMap<>();
        name.put("type", "keyword");

        Map<String, Object> timestamp = new HashMap<>();
        timestamp.put("type", "date");

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("created_at", timestamp);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        createIndexRequest.mapping(mapping);

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        var index = "posts_not_sorted";

        int bulkSize = 10000;
        System.out.println(">> Starting");
        for(int i =0; i < 1000000; i++){
            System.out.println(">> processing " + i);
            BulkRequest createBulkPosts = new BulkRequest();
            int bulkCounter=0;
            System.out.println(">> Creating Bulk ");
            while(bulkCounter < bulkSize) {
                createBulkPosts.add(new IndexRequest(index).id(i + "")
                        .source(createDocumentMap("post " + i , new Date())));
                i++;
                bulkCounter++;
            }
            bulkCounter = 0;
            createBulkPosts.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
            createBulkPosts.setRefreshPolicy("wait_for");
            System.out.println(">> Sending Bulk ");
            BulkResponse bulkResponse = client.bulk(createBulkPosts, RequestOptions.DEFAULT);
            System.out.println(">> Completed Bulk ");
        }

        client.close();

    }

    public static Map<String, Object> createDocumentMap(String name, Date createdAt){
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);
        jsonMap.put("created_at", createdAt);
        return jsonMap;
    }


}
