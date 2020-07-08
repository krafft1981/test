package com.work.test.service;

import com.work.test.dao.AuthorEntity;
import com.work.test.dao.AuthorRepository;
import com.work.test.dao.BookEntity;
import com.work.test.dao.BookRepository;
import com.work.test.dto.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Integer createAuthor(@NonNull Author author) {

        AuthorEntity entity = new AuthorEntity();

        entity.setFio(author.getFio());
        entity.setBirthYear(author.getBirthYear());

        author.getBooks().stream().forEach(id -> {
            BookEntity book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                entity.addBook(book);
            }
        });

        return authorRepository.saveAndFlush(entity).getId();
    }

    public List<Author> findById(Integer id) {
        if (id != null) {
            List<Author> authorList = new ArrayList<>();
            AuthorEntity entity = authorRepository.findById(id).orElse(null);
            if (entity != null) {
                authorList.add(daoToDto(entity));
            }
            return authorList;
        }
        else {
            return authorRepository
                    .findAll()
                    .stream().map(entity -> { return daoToDto(entity); })
                    .collect(Collectors.toList());
        }
    }

    public void updateAuthor(@NonNull Author author) {

        AuthorEntity entity = authorRepository.findById(author.getId()).orElse(null);
        if (entity != null) {
            authorRepository.saveAndFlush(dtoToDao(author));
        }
    }

    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    private Author daoToDto(AuthorEntity entity) {

        Author author = new Author();

        author.setId(entity.getId());
        author.setFio(entity.getFio());
        author.setBirthYear(entity.getBirthYear());

        entity.getBooks()
              .stream()
              .forEach( book -> { author.addBook(book.getId());});

        return author;
    }

    private AuthorEntity dtoToDao(Author author) {

        AuthorEntity entity = new AuthorEntity();

        entity.setId(author.getId());
        entity.setFio(author.getFio());
        entity.setBirthYear(author.getBirthYear());

        author.getBooks().stream().forEach(id -> {
            BookEntity book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                entity.addBook(book);
            }
        });

        return entity;
    }
}
