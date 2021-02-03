package com.row49382.demosessiontwo.service;

import com.row49382.demosessiontwo.exception.RecordNotFoundException;
import com.row49382.demosessiontwo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id) throws RecordNotFoundException;
    Book createOrUpdateBook(Book entity);
    void deleteBookById(long id) throws RecordNotFoundException;
}
