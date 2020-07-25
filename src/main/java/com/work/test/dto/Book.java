package com.work.test.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Integer id;
    private String name;
    private Integer publishingYear;
    private String annotation;
    private List<Integer> authors;

    public void addAuthor(Integer id) {
        authors.add(id);
    }

    public Book() {
        authors = new ArrayList<>();
    }
}
