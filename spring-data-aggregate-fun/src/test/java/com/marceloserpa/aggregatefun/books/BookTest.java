package com.marceloserpa.aggregatefun.books;

import com.marceloserpa.aggregatefun.DataBaseConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static java.util.Collections.singletonMap;

@Testcontainers
@SpringBootTest(classes = DataBaseConfig.class)
@AutoConfigureDataJdbc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookTest {

    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            "postgres:11.1")
            .withDatabaseName("mypoc")
            .withUsername("marceloserpa")
            .withPassword("123456")
            .withInitScript("init.sql")
            ;

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


    @BeforeAll
    public static void start(){
       container.start();
    }

    @AfterAll
    public static void close(){
        container.stop();
        container.close();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}