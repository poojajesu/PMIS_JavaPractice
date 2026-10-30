package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Optional<Book> updateBook(Long bookId, Book updatedBook) {
        return bookRepository.findById(bookId).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            existingBook.setAvailable(updatedBook.isAvailable());

            return bookRepository.save(existingBook);
        });
    }

    public boolean deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }
}