package com.marceloserpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(IntegrationSetupExtension.class)
class BookService2Test {

    @Test
    public void test(){
        var books = new BookService().getAll();
        assertTrue(books.size() == 1);
    }

}