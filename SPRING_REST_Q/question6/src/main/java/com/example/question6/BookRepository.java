package com.example.question6;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public class BookRepository {

    public List<Book> findByTitleAndAuthor(
            String title, String author) {

        return BookData.books().stream()

                .filter(book ->
                        book.title().toLowerCase(Locale.ROOT)
                                .startsWith(title.toLowerCase(Locale.ROOT)))

                .filter(book ->
                        author == null ||
                                book.author().toLowerCase(Locale.ROOT)
                                        .startsWith(author.toLowerCase(Locale.ROOT)))

                .toList();
    }
}