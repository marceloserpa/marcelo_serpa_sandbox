package com.marceloserpa;

import java.util.ArrayList;
import java.util.List;

public class FakeDB {

    private static List<Book> books = new ArrayList<>();

    public void save(Book book) {
        FakeDB.books.add(book);
    }

    public List<Book> getAll(){
        return FakeDB.books;
    }


}
