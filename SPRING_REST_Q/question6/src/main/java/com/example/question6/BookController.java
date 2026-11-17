package com.example.question6;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;

    }

    @GetMapping("/search")
    public List<Book> searchBooks(

            @RequestParam(required = false) String title,

            @RequestParam(required = false) String author) {

        return bookService.search(title, author);

    }
}