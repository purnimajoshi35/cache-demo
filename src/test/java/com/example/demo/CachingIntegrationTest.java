package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {EhCacheTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CachingIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JCacheCacheManager cacheManager;

    @Test
    public void should_call_only_once() {
        Book b1 = new Book("1", "Book 1", "Atul", "Atul' book");
        Book b2 = new Book("2", "Book 2", "Anuj", "Atul' book");
        Book b3 = new Book("3", "Book 3", "Amar", "Atul' book");

        bookRepository.save(b1);
        bookRepository.save(b2);
        List<Book> books1 = bookRepository.findAll();

        bookRepository.save(b3);
        List<Book> books2 = bookRepository.findAll();

        assertThat(books1).isEqualTo(books2);
    }

    @Test
    public void should_call_method_twice() throws InterruptedException {
        Book b1 = new Book("1", "Book 1", "Atul", "Atul' book");
        Book b2 = new Book("2", "Book 2", "Anuj", "Atul' book");
        Book b3 = new Book("3", "Book 3", "Amar", "Atul' book");

        bookRepository.save(b1);
        bookRepository.save(b2);
        List<Book> books1 = bookRepository.findAll();

        TimeUnit.SECONDS.sleep(6);

        bookRepository.save(b3);
        List<Book> books2 = bookRepository.findAll();

        assertThat(books2.size()).isGreaterThan(books1.size());
    }
}
