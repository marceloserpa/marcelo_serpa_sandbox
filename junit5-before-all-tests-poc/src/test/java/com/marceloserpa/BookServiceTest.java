package com.marceloserpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(IntegrationSetupExtension.class)
class BookServiceTest {

    @Test
    public void test(){
        var books = new BookService().getAll();
        assertTrue(books.size() == 1);
    }

}