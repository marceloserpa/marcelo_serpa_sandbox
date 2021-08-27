package com.marceloserpa.poc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

public class HelloTestContainer {

    private static ElasticsearchContainer container;

    @BeforeAll
    public static void setup() {
        container = new ElasticsearchContainer(
                DockerImageName
                        .parse("docker.elastic.co/elasticsearch/elasticsearch-oss")
                        .withTag("7.8.0"));
        container.start();
    }

    @Test
    public void shouldCallClusterHealthCheckApi() throws IOException {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(container.getHttpHostAddress()));
        RestHighLevelClient highLevelClient = new RestHighLevelClient(builder);

        ClusterHealthRequest request = new ClusterHealthRequest();
        ClusterHealthResponse health = highLevelClient.cluster().health(request, RequestOptions.DEFAULT);

        Assertions.assertEquals(ClusterHealthStatus.GREEN, health.getStatus());
    }

    @AfterAll
    public static void destroy() {
        if (container != null) {
            container.close();
        }
    }


}
