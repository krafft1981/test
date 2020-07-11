package com.work.test.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    private String name;
    private Integer publishingYear;
    private String annotation;
    private List<Integer> authors;

    public void addAuthor(Integer id) {
        authors.add(id);
    }
}
