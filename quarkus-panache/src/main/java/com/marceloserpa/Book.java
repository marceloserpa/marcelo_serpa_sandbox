package com.marceloserpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity(name = "books")
public class Book extends PanacheEntity {

    public long id;
    public String title;

}