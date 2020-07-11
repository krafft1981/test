package com.work.test.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Integer id;
    private String name;
    private Integer publishingYear;
    private String annotation;
    private List<Integer> authors = new ArrayList<>();

    public void addAuthor(Integer id) {
        authors.add(id);
    }
}
