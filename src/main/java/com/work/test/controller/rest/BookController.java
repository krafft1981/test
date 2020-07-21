package com.work.test.controller.rest;

import com.work.test.dto.Book;
import com.work.test.service.BookService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> doGetBookRequest(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
            return bookService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutBookRequest(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "publishingYear", required = true) Integer publishingYear,
            @RequestParam(value = "annotation", required = true) String annotation,
            @RequestParam(value = "authors", required = true) Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Book book = new Book(id, name, publishingYear, annotation, Arrays.asList(authors));
        bookService.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostBookRequest(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "publishingYear", required = true) Integer publishingYear,
            @RequestParam(value = "annotation", required = true) String annotation,
            @RequestParam(value = "authors", required = true) Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Book book = new Book(null, name, publishingYear, annotation, Arrays.asList(authors));
        return bookService.createBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteBookRequest(
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        bookService.deleteBook(id);
    }
}
