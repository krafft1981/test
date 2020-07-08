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

    @Override
    public String toString() {
        StringBuilder authorString = new StringBuilder();
        books.stream().forEach(str -> { authorString.append(str + " "); });
        return String.format(
                "Book[id=%d, fio='%s', birthYear='%d', books='%s']",
                id, fio, birthYear, authorString);
    }
}
