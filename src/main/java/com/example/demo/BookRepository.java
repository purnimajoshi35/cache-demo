package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    @Cacheable(value = "bookCache")
    List<Book> findAll();

    Book save(Book book);
}
