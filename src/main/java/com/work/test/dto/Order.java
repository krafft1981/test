package com.work.test.dto;

import java.text.DateFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private String name;
    private List<Integer> books;
    private DateFormat startedAt;
    private DateFormat stoppedAt;
    private boolean finished;

    public void addBook(Integer id) {
        books.add(id);
    }
}
