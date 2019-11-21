package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/addBook/{id}/{title}/{author}/{description}")
    public String addBook(@PathVariable String id, @PathVariable String title, @PathVariable String author, @PathVariable String description)
    {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);

        bookRepository.save(book);

        return "Book added successfully";
    }

    @RequestMapping("/getAllBooks")
    public List<Book> getAllBook()
    {
        List<Book> bookList = bookRepository.findAll();

        return bookList;
    }



}
