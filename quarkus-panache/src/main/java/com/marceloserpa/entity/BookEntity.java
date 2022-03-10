package com.marceloserpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity(name = "books")
public class BookEntity extends PanacheEntity {

    public String title;

}