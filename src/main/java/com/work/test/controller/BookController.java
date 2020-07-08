package com.work.test.controller;

import com.work.test.dto.Book;
import com.work.test.service.BookService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> doGetBookRequest(
            Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
            return bookService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutBookRequest(
            @NonNull Integer id,
            @NonNull String name,
            @NonNull Integer publishingYear,
            @NonNull String annotation,
            @NonNull Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setPublishingYear(publishingYear);
        book.setAnnotation(annotation);
        book.setAuthors(Arrays.asList(authors));
        bookService.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostBookRequest(
            @NonNull final String name,
            @NonNull final Integer publishingYear,
            @NonNull final String annotation,
            @NonNull final Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Book book = new Book();
        book.setName(name);
        book.setPublishingYear(publishingYear);
        book.setAnnotation(annotation);
        book.setAuthors(Arrays.asList(authors));
        return bookService.createBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteBookRequest(
            @NonNull Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        bookService.deleteBook(id);
    }
}
