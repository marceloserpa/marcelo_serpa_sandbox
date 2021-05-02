package com.marceloserpa.graphql.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {

    private final List<LinkModel> links = new ArrayList<>();

    public LinkService() {
        links.add(new LinkModel("https://www.netifi.com", "Autonomous microservices platform"));
        links.add(new LinkModel("http://howtographql.com", "Your favorite GraphQL page"));
        links.add(new LinkModel("http://graphql.org/learn/", "The official docks"));
    }

    public List<LinkModel> findAll() {
        return links;
    }


}
