package com.work.test.mvcController;

import com.work.test.dto.Customer;
import com.work.test.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value="/mvc")
@Controller
public class MvcCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = { "/customer" })
    public String mvcCustomerGet(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customer";
    }

    @GetMapping(value = { "/customer/delete" })
    public String mvcCustomerDelete(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        customerService.deleteCustomer(id);
        model.addAttribute("customers", customerService.getAll());
        return "customer";
    }

    @GetMapping(value = { "/customer/edit" })
    public String mvcCustomerEdit(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        List<Customer> customerList = customerService.findById(id);
        if (customerList.isEmpty() != true) {
            model.addAttribute("customer", customerList.get(0));
            return "customer-edit";
        }

        model.addAttribute("customers", customerService.getAll());
        return "customer";
    }

    @PostMapping("/customer/update")
    public String mvcCustomerUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @Valid Customer customer,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "customer-edit";
        }

        customerService.updateCustomer(customer);
        model.addAttribute("customers", customerService.getAll());
        return "customer";
    }

    @PostMapping("/customer/insert")
    public String mvcCustomerInsert(
            @Valid Customer customer,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "customer-edit";
        }

        customerService.createCustomer(customer);
        model.addAttribute("customers", customerService.getAll());
        return "customer";
    }

    @GetMapping("/customer/add")
    public String mvcCustomerAdd(
            Customer customer) {
        return "customer-add";
    }
}
