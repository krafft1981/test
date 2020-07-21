package com.work.test.controller;

import com.work.test.dto.Author;
import com.work.test.service.AuthorService;
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
public class MvcAuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(value = { "/author" })
    public String mvcAuthorGet(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "author";
    }

    @GetMapping(value = { "/author/delete" })
    public String mvcAuthorDelete(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        authorService.deleteAuthor(id);
        model.addAttribute("authors", authorService.getAll());
        return "author";
    }

    @GetMapping(value = { "/author/edit" })
    public String mvcAuthorEdit(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        List<Author> authorList = authorService.findById(id);
        if (authorList.isEmpty() != true) {
            model.addAttribute("author", authorList.get(0));
            return "author-edit";
        }

        model.addAttribute("authors", authorService.getAll());
        return "author";
    }

    @PostMapping("/author/update")
    public String mvcAuthorUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @Valid Author author,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "author-edit";
        }

        authorService.updateAuthor(author);
        model.addAttribute("authors", authorService.getAll());
        return "author";
    }

    @PostMapping("/author/insert")
    public String mvcAuthorInsert(
            @Valid Author author,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "author-edit";
        }

        authorService.createAuthor(author);
        model.addAttribute("authors", authorService.getAll());
        return "author";
    }

    @GetMapping("/author/add")
    public String mvcAuthorAdd(
            Author author) {
        return "author-add";
    }
}
