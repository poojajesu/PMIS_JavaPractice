package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.incrementAndGet());
        }

        books.put(book.getId(), book);
        return book;
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public boolean existsById(Long id) {
        return books.containsKey(id);
    }

    public void deleteById(Long id) {
        books.remove(id);
    }
}