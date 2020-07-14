package com.work.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.DateFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;
    private String name;
    private Integer customerId;
    private List<Integer> books;
    private DateFormat startedAt;
    private DateFormat stoppedAt;
    private boolean finished;

    public void addBook(Integer id) {
        books.add(id);
    }

    public Order(Integer id, String name, List<Integer> books) {

//        Order(id, name, null, books, null, null, false);
    }

    public Order(String name, Integer customerId, List<Integer> books) {
//        Order(null, name, customerId, books, null, null, false);
    }
}
