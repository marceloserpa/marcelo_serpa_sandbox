package com.marceloserpa;

import java.util.List;

public class BookService {

    public List<Book> getAll(){
        return new FakeDB().getAll();
    }
}
