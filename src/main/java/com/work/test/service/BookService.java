package com.work.test.service;

import com.work.test.dao.AuthorEntity;
import com.work.test.dao.AuthorRepository;
import com.work.test.dao.BookEntity;
import com.work.test.dao.BookRepository;
import com.work.test.dto.Book;
import com.work.test.exception.AuthorNotFoundException;
import com.work.test.exception.BookNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Integer createBook(@NonNull Book book) {

        BookEntity entity = new BookEntity(
                book.getId(),
                book.getName(),
                book.getPublishingYear(),
                book.getAnnotation(),
                new HashSet<>());

        book.getAuthors().stream().forEach(id -> {
            AuthorEntity author = authorRepository
                    .findById(id)
                    .orElseThrow(() -> new AuthorNotFoundException(id));
            entity.addAuthor(author);
        });

        return bookRepository.saveAndFlush(entity).getId();
    }

    public List<Book> findById(Integer id) {
        if (id != null) {
            List<Book> bookList = new ArrayList<>();
            BookEntity entity = bookRepository
                    .findById(id)
                    .orElseThrow(() -> new BookNotFoundException(id));
            bookList.add(daoToDto(entity));
            return bookList;
        }

        else {
            return getAll();
        }
    }

    public List<Book> getAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(entity -> { return daoToDto(entity); })
                .collect(Collectors.toList());
    }

    public void updateBook(@NonNull Book book) {

        BookEntity entity = bookRepository
                .findById(book.getId())
                .orElseThrow(() -> new BookNotFoundException(book.getId()));
        bookRepository.saveAndFlush(dtoToDao(book));
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    private Book daoToDto(BookEntity entity) {

        Book book = new Book(
                entity.getId(),
                entity.getName(),
                entity.getPublishingYear(),
                entity.getAnnotation(),
                new ArrayList<>());

        entity.getAuthors()
              .stream()
              .forEach( author -> {book.addAuthor(author.getId());});

        return book;
    }

    private BookEntity dtoToDao(Book book) {

        BookEntity entity = new BookEntity(
                book.getId(),
                book.getName(),
                book.getPublishingYear(),
                book.getAnnotation(),
                new HashSet<>());

        book.getAuthors().stream().forEach(id -> {
            AuthorEntity author = authorRepository
                    .findById(id)
                    .orElseThrow(() -> new AuthorNotFoundException(book.getId()));
            entity.addAuthor(author);
        });

        return entity;
    }
}
