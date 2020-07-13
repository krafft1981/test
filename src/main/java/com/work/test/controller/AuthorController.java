package com.work.test.controller;

import com.work.test.dto.Author;
import com.work.test.service.AuthorService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/author")
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Author> doGetAuthorRequest(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        return authorService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutAuthorRequest(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value =  "fio", required = true) String fio,
            @RequestParam(value =  "birthYear", required = true) Integer birthYear,
            @RequestParam(value =  "books", required = true) Integer[] books,
            HttpServletResponse response,
            HttpServletRequest request) {
        Author author = new Author(id, fio, birthYear, Arrays.asList(books));
        authorService.updateAuthor(author);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostAuthorRequest(
            @RequestParam(value = "fio", required = true) String fio,
            @RequestParam(value = "birthYear", required = true) Integer birthYear,
            @RequestParam(value = "books", required = true) Integer[] books,
            HttpServletResponse response,
            HttpServletRequest request) {
        Author author = new Author(null, fio, birthYear, Arrays.asList(books));
        return authorService.createAuthor(author);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteAuthorRequest(
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        authorService.deleteAuthor(id);
    }
}
