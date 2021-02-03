package com.row49382.demosessiontwo.controller;

import com.row49382.demosessiontwo.exception.RecordNotFoundException;
import com.row49382.demosessiontwo.model.Book;
import com.row49382.demosessiontwo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.getAllBooks();
        return new ResponseEntity(books, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) throws RecordNotFoundException {
        Book book = this.bookService.getBookById(id);
        return new ResponseEntity(book, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedOrCreatedBook = this.bookService.createOrUpdateBook(book);
        return new ResponseEntity(updatedOrCreatedBook, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") long id) throws RecordNotFoundException {
        this.bookService.deleteBookById(id);
        return ResponseEntity.ok("Book successfully deleted");
    }
}
