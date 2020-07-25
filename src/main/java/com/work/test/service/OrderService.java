package com.work.test.service;

import com.work.test.dao.BookEntity;
import com.work.test.dao.BookRepository;
import com.work.test.dao.CustomerEntity;
import com.work.test.dao.CustomerRepository;
import com.work.test.dao.OrderEntity;
import com.work.test.dao.OrderRepository;
import com.work.test.dto.Order;
import com.work.test.utils.exceptions.BookNotFoundException;
import com.work.test.utils.exceptions.CustomerNotFoundException;
import com.work.test.utils.exceptions.OrderNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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

    public void deleteOrder(Integer id) {

        OrderEntity entity = orderRepository.findById(id).orElse(null);
        if (entity.getFinished() == false) {
            orderRepository.deleteById(id);
        }
    }

    public void setFinished(Integer id) {

        OrderEntity entity = orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        entity.setFinished();
        entity.setStoppedAt(new Date(System.currentTimeMillis()));
        orderRepository.saveAndFlush(entity);
    }

    public void updateOrder(Order order) {

        OrderEntity entity = orderRepository
                .findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException(order.getId()));

        if (entity.getFinished() != true) {
            orderRepository.saveAndFlush(dtoToDao(order));
        }
    }

    public Integer createOrder(Order order) {

        OrderEntity entity = dtoToDao(order);
        entity.setStartedAt(new Date(System.currentTimeMillis()));
        return orderRepository.saveAndFlush(entity).getId();
    }

    public List<Order> findById(Integer id) {

        if (id != null) {
            List<Order> orderList = new ArrayList<>();
            OrderEntity entity = orderRepository
                    .findById(id)
                    .orElseThrow(() -> new OrderNotFoundException(id));
            orderList.add(daoToDto(entity));
            return orderList;
        }

        else {
            return getAll();
        }
    }

    public List<Order> getAll() {
        return orderRepository
                .findAll()
                .stream().map(entity -> { return daoToDto(entity); })
                .collect(Collectors.toList());
    }

    private Order daoToDto(OrderEntity entity) {

        Order order = new Order();
        order.setId(entity.getId());
        order.setCustomerId(entity.getCustomer().getId());
        order.setName(entity.getName());
        order.setFinished(entity.getFinished());
        order.setStartedAt(entity.getStartedAt());
        order.setStoppedAt(entity.getStoppedAt());
        entity.getBooks()
                .stream()
                .forEach(bookEntity -> {
                    order.addBook(bookEntity.getId());
                });

        return order;
    }

    private OrderEntity dtoToDao(Order order) {

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setName(order.getName());
        entity.setFinished(order.isFinished());
        Integer customerId = order.getCustomerId();
        CustomerEntity customerEntity = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        entity.setCustomer(customerEntity);
        order.getBooks()
                .stream()
                .forEach(bookId -> {
                    BookEntity bookEntity = bookRepository
                            .findById(bookId)
                            .orElseThrow(() -> new BookNotFoundException(bookId));
                    entity.addBook(bookEntity);
                });
        return entity;
    }
}
