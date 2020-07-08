package com.work.test.controller;

import com.work.test.dto.Customer;
import com.work.test.service.CustomerService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> doGetCustomerRequest(
            Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
//        return CustomerService.findById(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void doPutCustomerRequest(
            @NonNull Integer id,
            @NonNull String name,
            @NonNull Integer publishingYear,
            @NonNull String annotation,
            @NonNull Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Customer Customer = new Customer();
        Customer.setId(id);
        Customer.setName(name);
//        Customer.setPublishingYear(publishingYear);
//        Customer.setAnnotation(annotation);
//        Customer.setAuthors(Arrays.asList(authors));
//        CustomerService.updateCustomer(Customer);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer doPostCustomerRequest(
            @NonNull final String name,
            @NonNull final Integer publishingYear,
            @NonNull final String annotation,
            @NonNull final Integer[] authors,
            HttpServletResponse response,
            HttpServletRequest request) {
        Customer Customer = new Customer();
        Customer.setName(name);
//        Customer.setPublishingYear(publishingYear);
//        Customer.setAnnotation(annotation);
//        Customer.setAuthors(Arrays.asList(authors));
//        return CustomerService.createCustomer(Customer);
        return 0;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void doDeleteCustomerRequest(
            @NonNull Integer id,
            HttpServletResponse response,
            HttpServletRequest request) {
//        CustomerService.deleteCustomer(id);
    }
}
