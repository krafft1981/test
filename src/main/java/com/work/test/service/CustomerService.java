package com.work.test.service;

import com.work.test.dao.CustomerEntity;
import com.work.test.dao.CustomerRepository;
import com.work.test.dao.OrderRepository;
import com.work.test.dto.Customer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void deleteCustomer(Integer id) {

        customerRepository.deleteById(id);
    }

    public void updateCustomer(@NonNull Customer customer) {

        CustomerEntity entity = customerRepository.findById(customer.getId()).orElse(null);
        if (entity != null) {
            customerRepository.saveAndFlush(dtoToDao(customer));
        }
    }

    public List<Customer> findById(Integer id, boolean finished) {

        return null;
    }

    public Integer createCustomer(@NonNull Customer customer) {

        CustomerEntity entity = dtoToDao(customer);
        return customerRepository.saveAndFlush(entity).getId();
    }

    private Customer daoToDto(CustomerEntity entity) {

        Customer customer = new Customer(
                entity.getId(),
                entity.getName(),
                entity.getPhone());

//        entity.getBooks()
//                .stream()
//                .forEach( book -> { author.addBook(book.getId()); });

        return customer;
    }

    private CustomerEntity dtoToDao(Customer customer) {

        CustomerEntity entity = new CustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                new HashSet<>());

        return entity;
    }
}
