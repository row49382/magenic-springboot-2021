package com.row49382.demosessiontwo.service;

import com.row49382.demosessiontwo.exception.RecordNotFoundException;
import com.row49382.demosessiontwo.model.Book;
import com.row49382.demosessiontwo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();

        if (books.size() > 0) {
            return books;
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public Book getBookById(Long id) throws RecordNotFoundException {
        Optional<Book> book = this.bookRepository.findById(id);

        return book.orElseThrow(
                () -> new RecordNotFoundException(
                        String.format("No book record exists for given id %d", id)));
    }

    @Override
    public Book createOrUpdateBook(Book entity) {
        Optional<Book> book = this.bookRepository.findById(entity.getId());

        if (book.isPresent()) {
            Book newEntity = book.get();
            newEntity.setAuthor(entity.getAuthor());
            newEntity.setTitle(entity.getTitle());

            newEntity = this.bookRepository.save(newEntity);
            return newEntity;
        }
        else {
            entity = this.bookRepository.save(entity);
            return entity;
        }
    }

    @Override
    public void deleteBookById(long id) throws RecordNotFoundException {
        Optional<Book> book = this.bookRepository.findById(id);

        if (book.isPresent()) {
            this.bookRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException(
                    String.format("No book record exists for given id %d", id));
        }
    }
}
