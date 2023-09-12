package com.marceloserpa.springenvers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long>,
        RevisionRepository<BookEntity, Long, Long> {
}
