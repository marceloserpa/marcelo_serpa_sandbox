package com.marceloserpa.poc;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ApplicationIngestion {

    private static final Logger log = LogManager.getLogger(ApplicationIngestion.class);
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        List<String> tenants = Arrays.asList("hackernews", "infoq");
        PutIndexTemplateRequest indexTemplateRequest = createIndexTemplateRequest(tenants);
        client.indices()
                .putTemplate(indexTemplateRequest, RequestOptions.DEFAULT);
        log.info("Index Template created.");

        Post post1 = new Post();
        post1.setTenant("hackernews");
        post1.setTitle("Lorem Ipsum");
        post1.setSlug("lorem-ipsum");
        post1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        post1.setCreateAt(LocalDate.now());

        Post post2 = new Post();
        post2.setTenant("hackernews");
        post2.setTitle("Bibendum at varius");
        post2.setSlug("bibendum-at-varius");
        post2.setContent("Bibendum at varius vel pharetra vel turpis. Lacinia at quis risus sed vulputate odio ut enim. Malesuada proin libero nunc consequat interdum varius sit amet.");
        post2.setCreateAt(LocalDate.now());


        Post post3 = new Post();
        post3.setTenant("infoq");
        post3.setTitle("Lorem ipsum");
        post3.setSlug("lorem-ipsum-2");
        post3.setContent("Quis viverra nibh cras pulvinar mattis nunc sed blandit libero. Arcu vitae elementum curabitur vitae nunc sed velit dignissim sodales. A pellentesque sit amet porttitor.");
        post3.setCreateAt(LocalDate.now());

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest("blog-article-2022").id("1").source(postToMap(post1)));
        bulkRequest.add(new IndexRequest("blog-article-2022").id("2").source(postToMap(post2)));
        bulkRequest.add(new IndexRequest("blog-article-2022").id("3").source(postToMap(post3)));

        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("Created all Posts");

        client.close();

    }

    private static PutIndexTemplateRequest createIndexTemplateRequest(List<String> tenants) {
        PutIndexTemplateRequest request = new PutIndexTemplateRequest("blog-articles-template");
        request.patterns(Arrays.asList("blog-article-*"));

        // settings
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 2));


        // mappings
        Map<String, Object> tenant = new HashMap<>();
        tenant.put("type", "keyword");

        Map<String, Object> title = new HashMap<>();
        title.put("type", "text");

        Map<String, Object> content = new HashMap<>();
        content.put("type", "text");

        Map<String, Object> slug = new HashMap<>();
        slug.put("type", "keyword");

        Map<String, Object> createAt = new HashMap<>();
        createAt.put("type", "date");

        Map<String, Object> properties = new HashMap<>();
        properties.put("tenant", tenant);
        properties.put("title", title);
        properties.put("slug", slug);
        properties.put("content", content);
        properties.put("create_at", createAt);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);

        // aliases
        String aliasPattern = "{index}-%s-alias";
        String alias = "";
        for(String currentTenant : tenants) {
            alias = String.format(aliasPattern, currentTenant);
            request.alias(new Alias(alias).filter(QueryBuilders.termQuery("tenant", currentTenant)));
        }

        return request;
    }

    private static Map<String, String> postToMap(Post post){
        Map<String, String> data = new HashMap<>();
        data.put("tenant", post.getTenant());
        data.put("title", post.getTitle());
        data.put("content", post.getContent());
        data.put("create_at", post.getCreateAt().format(FORMATTER));
        return data;
    }

    public static Map<String, Object> createDocumentMap(String name, LocalDate productionDate, Double[] point) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", name);

        Date date = Date.from(productionDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        jsonMap.put("production_date", date);
        jsonMap.put("location", point);
        return jsonMap;
    }

}
