package com.work.test.service;

import com.work.test.dao.AuthorEntity;
import com.work.test.dao.AuthorRepository;
import com.work.test.dao.BookEntity;
import com.work.test.dao.BookRepository;
import com.work.test.dto.Book;
import java.util.ArrayList;
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

        BookEntity entity = new BookEntity();

        entity.setPublishingYear(book.getPublishingYear());
        entity.setAnnotation(book.getAnnotation());
        entity.setName(book.getName());

        book.getAuthors().stream().forEach(id -> {
            AuthorEntity author = authorRepository.findById(id).orElse(null);
            if (author != null) {
                entity.addAuthor(author);
            }
        });

        return bookRepository.saveAndFlush(entity).getId();
    }

    public List<Book> findById(Integer id) {
        if (id != null) {
            List<Book> bookList = new ArrayList<>();
            BookEntity entity = bookRepository.findById(id).orElse(null);
            if (entity != null) {
                bookList.add(daoToDto(entity));
            }
            return bookList;
        }

        else {
            return bookRepository
                    .findAll()
                    .stream().map(entity -> { return daoToDto(entity); })
                    .collect(Collectors.toList());
        }
    }

    public void updateBook(@NonNull Book book) {

        BookEntity entity = bookRepository.findById(book.getId()).orElse(null);
        if (entity != null) {
            bookRepository.saveAndFlush(dtoToDao(book));
        }
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    private Book daoToDto(BookEntity entity) {

        Book book = new Book();
        book.setId(entity.getId());
        book.setAnnotation(entity.getAnnotation());
        book.setName(entity.getName());
        book.setPublishingYear(entity.getPublishingYear());
        entity.getAuthors()
              .stream()
              .forEach( author -> {book.addAuthor(author.getId());});

        return book;
    }

    private BookEntity dtoToDao(Book book) {

        BookEntity entity = new BookEntity();
        entity.setName(book.getName());
        entity.setAnnotation(book.getAnnotation());
        entity.setPublishingYear(book.getPublishingYear());
        entity.setId(book.getId());
        book.getAuthors().stream().forEach(id -> {
            AuthorEntity author = authorRepository.findById(id).orElse(null);
            if (author != null) {
                entity.addAuthor(author);
            }
        });

        return entity;
    }
}
