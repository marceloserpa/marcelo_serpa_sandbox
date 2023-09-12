package com.marceloserpa.springenvers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }

    public void save(BookEntity entity) {
        bookRepository.save(entity);
    }

    public void update(BookEntity entity) {
        bookRepository.save(entity);
    }
}
