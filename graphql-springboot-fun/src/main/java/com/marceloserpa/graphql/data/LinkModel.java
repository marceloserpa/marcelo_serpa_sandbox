package com.marceloserpa.graphql.data;

public class LinkModel {

    private String url;
    private String description;

    public LinkModel() {
    }

    public LinkModel(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
