package com.marceloserpa.es8;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class CreateIndex {

    public static void main(String[] args) throws IOException {


        String serverUrl = "http://localhost:9200";

        RestClient restClient = RestClient.builder(HttpHost.create(serverUrl)).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchClient esClient = new ElasticsearchClient(transport);

        esClient.indices().create(c -> c
                .index("books")
        );


        Book book = new Book(1L, "The Shining", "Stephen King");

        IndexResponse response = esClient.index(i -> i
                .index("books")
                .id(String.valueOf(book.id()))
                .document(book)
        );

        System.out.println("Indexed with version " + response.version());

        esClient.close();

    }

}