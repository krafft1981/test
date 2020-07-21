package com.work.test.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
