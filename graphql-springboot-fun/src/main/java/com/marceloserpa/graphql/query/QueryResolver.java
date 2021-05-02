package com.marceloserpa.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.marceloserpa.graphql.data.LinkModel;
import com.marceloserpa.graphql.data.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private LinkService linkService;

    public List<LinkModel> allLinks() {
        return linkService.findAll();
    }

}
