package com.work.test.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    private Integer id;
    private String fio;
    private Integer birthYear;
    private List<Integer> books = new ArrayList<>();

    public void addBook(Integer id) {
        books.add(id);
    }
}
