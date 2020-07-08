package com.work.test.controller;

import com.work.test.dto.Book;
import com.work.test.dto.Order;
import com.work.test.service.OrderService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> doGetOrderRequest(
            Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutOrderRequest(
            @NonNull Integer id,
            @NonNull String name,
            @NonNull Integer publishingYear,
            @NonNull String annotation,
            @NonNull Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostOrderRequest(
            @NonNull final String name,
            @NonNull final Integer publishingYear,
            @NonNull final String annotation,
            @NonNull final Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        return 0;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteOrderRequest(
            @NonNull Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
//        OrderService.deleteBook(id);
    }
}
