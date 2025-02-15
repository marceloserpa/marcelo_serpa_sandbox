package com.marceloserpa.es8;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ConcurrentUpdates {

    public static void main(String[] args) throws IOException {

        String serverUrl = "http://localhost:9200";

        RestClient restClient = RestClient.builder(HttpHost.create(serverUrl)).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        ElasticsearchClient esClient = new ElasticsearchClient(transport);


        String searchText = "Shining";

        SearchResponse<Book> response = esClient.search(s -> s
                        .index("books")
                      //  .storedFields("_seq_no", "_primary_term") // Retrieve versioning info

                        .query(q -> q
                                .match(t -> t
                                        .field("title")
                                        .query(searchText)
                                )
                        ).seqNoPrimaryTerm(true),
                Book.class
        );

        System.out.println(response.hits().hits().size());

        Hit<Book> bookHit = response.hits().hits().stream().findFirst().get();

        Long seqNo = bookHit.seqNo();
        Long primaryTerm = bookHit.primaryTerm();
        Book originalBook = bookHit.source();

        System.out.println("seqNo = " + seqNo + "; primaryTerm=" + primaryTerm );


        Book bookUpdate1 = new Book(originalBook.id(), originalBook.title(), originalBook.author() + "oi");

        Book bookUpdate2 = new Book(originalBook.id(), originalBook.title()+"hello", originalBook.author());

        System.out.println("Trying to update the first book = SeqNo = " + seqNo + " primary="+primaryTerm);
        esClient.update(u -> u
                        .index("books")
                        .id(bookUpdate1.id().toString())
                        .ifPrimaryTerm(primaryTerm)
                        .ifSeqNo(seqNo)
                        .doc(bookUpdate1),
                Book.class
        );


        System.out.println("Trying to update the first book = SeqNo = " + seqNo + " primary="+primaryTerm);
        esClient.update(u -> u
                        .index("books")
                        .id(bookUpdate2.id().toString())
                        .ifPrimaryTerm(primaryTerm)
                        .ifSeqNo(seqNo)
                        .doc(bookUpdate2),
                Book.class
        );

        esClient.close();



    }
}
