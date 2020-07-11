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
public class Customer {

    private Integer id;
    private String name;
    private String phone;
    private List<Integer> orders;

    public void addOrder(Integer id) {
        orders.add(id);
    }
}
