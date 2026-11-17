package com.example.question6;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;

    }

    public List<Book> search(String title, String author) {

        if (title == null || title.isBlank()) {

            throw new IllegalArgumentException(
                    "title must not be blank");

        }

        String cleanTitle = title.trim();

        String cleanAuthor =
                author == null ? null : author.trim();

        List<Book> books =
                bookRepository.findByTitleAndAuthor(
                        cleanTitle, cleanAuthor);

        if (books.isEmpty()) {

            throw new BookNotFoundException("No books found");

        }

        return books;
    }
}