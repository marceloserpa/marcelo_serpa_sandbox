package com.marceloserpa.aggregatefun.books;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AuthorRepository extends CrudRepository<Author, Long> {
}
