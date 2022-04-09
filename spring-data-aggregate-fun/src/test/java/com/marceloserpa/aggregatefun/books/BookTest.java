package com.marceloserpa.aggregatefun.books;

import com.marceloserpa.aggregatefun.DataBaseConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = DataBaseConfig.class)
@AutoConfigureDataJdbc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void booksAndAuthors() {

        Author author = new Author();
        author.name = "Greg L. Turnquist";

        author = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Spring Boot");
        book.addAuthor(author);

        bookRepository.save(book);

        bookRepository.deleteAll();

        Assertions.assertEquals(authorRepository.count(), 1);
    }

}