package com.work.test.mvcController;

import com.work.test.dto.Book;
import com.work.test.service.BookService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value="/mvc")
@Controller
public class MvcBookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = { "/book" })
    public String mvcBookGet(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @GetMapping(value = { "/book/delete" })
    public String mvcBookDelete(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @GetMapping(value = { "/book/edit" })
    public String mvcBookEdit(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        List<Book> bookList = bookService.findById(id);
        if (bookList.isEmpty() != true) {
            model.addAttribute("book", bookList.get(0));
            return "book-edit";
        }

        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @PostMapping("/book/update")
    public String mvcBookUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @Valid Book book,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "book-edit";
        }

        bookService.updateBook(book);
        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @PostMapping("/book/insert")
    public String mvcBookInsert(
            @Valid Book book,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "author-edit";
        }

        bookService.createBook(book);
        model.addAttribute("books", bookService.getAll());
        return "book";
    }

    @GetMapping("/book/add")
    public String mvcBookAdd(
            Book boo) {
        return "book-add";
    }
}
