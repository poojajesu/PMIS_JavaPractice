package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class UpdateBookController {

    private final BookService bookService;

    public UpdateBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long bookId,
            @RequestBody Book book) {

        return bookService.updateBook(bookId, book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}