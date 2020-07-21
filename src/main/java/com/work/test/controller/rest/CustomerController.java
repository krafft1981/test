package com.work.test.controller.rest;

import com.work.test.dto.Customer;
import com.work.test.service.CustomerService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> doGetCustomerRequest(
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        return customerService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutCustomerRequest(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "phone", required = true) String phone,
            HttpServletResponse response,
            HttpServletRequest request) {
        Customer customer = new Customer(id, name, phone);
        customerService.updateCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostCustomerRequest(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "phone", required = true) String phone,
            HttpServletResponse response,
            HttpServletRequest request) {
        Customer customer = new Customer(null, name, phone);
        return customerService.createCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteCustomerRequest(
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
        customerService.deleteCustomer(id);
    }
}
