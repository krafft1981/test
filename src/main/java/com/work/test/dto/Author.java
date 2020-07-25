package com.work.test.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {

    private Integer id;
    private String fio;
    private Integer birthYear;
    private List<Integer> books;

    public void addBook(Integer id) {
        books.add(id);
    }

    public Author() {
        books = new ArrayList<>();
    }
}
