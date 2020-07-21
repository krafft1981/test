package com.work.test.controller;

import com.work.test.dto.Order;
import com.work.test.service.OrderService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> doGetOrderRequest(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        return orderService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutOrderRequest(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "books", required = false) Integer[] bookIds,
            @RequestParam(value = "finished", required = false) boolean finished,
            HttpServletResponse response,
            HttpServletRequest request) {
            Order order = new Order(id, name, Arrays.asList(bookIds));
            if (finished == true) {
                orderService.setFinished(id);
            }

            orderService.updateOrder(order);
    }

    @RequestMapping(value="/finished", method = RequestMethod.POST)
    public void doPostOrderFinishedRequest(
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        orderService.setFinished(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostOrderRequest(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "customer", required = true) Integer customerId,
            @RequestParam(value = "books", required = true) Integer[] bookIds,
            HttpServletResponse response,
            HttpServletRequest request) {
        Order order = new Order(name, customerId, Arrays.asList(bookIds));
        return orderService.createOrder(order);
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteOrderRequest(
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        orderService.deleteOrder(id);
    }
}
