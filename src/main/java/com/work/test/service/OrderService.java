package com.work.test.service;

import com.work.test.dao.BookEntity;
import com.work.test.dao.BookRepository;
import com.work.test.dao.CustomerRepository;
import com.work.test.dao.OrderEntity;
import com.work.test.dao.OrderRepository;
import com.work.test.dto.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Order> getById(Integer id) {

        return new ArrayList<>();
    }

    public void deleteOrder(Integer id) {

        orderRepository.deleteById(id);
    }

    public void setFinished(Integer id) {

    }

    public void updateOrder(Order order) {

    }

    public Integer createOrder(Order order) {

        OrderEntity entity = dtoToDao(order);
        return orderRepository.saveAndFlush(entity).getId();
    }

    private Order daoToDto(OrderEntity entity) {

        Order order = new Order();
        order.setId(entity.getCustomer().getId());
        order.setName(entity.getName());
        order.setFinished(entity.getFinished());
        order.setStartedAt(entity.getStartedAt());
        order.setStoppedAt(entity.getStoppedAt());
        entity.getBooks()
                .stream()
                .forEach( bookEntity -> { order.addBook(bookEntity.getId()); });
        return order;
    }

    private OrderEntity dtoToDao(Order order) {

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setName(order.getName());
        entity.setFinished(order.isFinished());
        entity.setCustomer(customerRepository.findById(order.getCustomerId()).orElse(null));
        order.getBooks()
                .stream()
                .forEach(bookId -> {
                    BookEntity bookEntity = bookRepository.findById(bookId).orElse(null);
                    if (bookEntity != null) {
                        entity.addBook(bookEntity);
                    }
                });

        return entity;
    }
}
