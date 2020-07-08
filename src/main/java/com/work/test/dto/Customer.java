package com.work.test.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Integer id;
    private String name;
    private String phone;
    private List<Integer> orders;

    public void addOrder(Integer id) {
        orders.add(id);
    }
}
