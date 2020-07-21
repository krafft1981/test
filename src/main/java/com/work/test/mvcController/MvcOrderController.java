package com.work.test.mvcController;

import com.work.test.dto.Order;
import com.work.test.service.OrderService;
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
public class MvcOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = { "/order" })
    public String mvcOrderGet(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "order";
    }

    @GetMapping(value = { "/order/delete" })
    public String mvcOrderDelete(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        orderService.deleteOrder(id);
        model.addAttribute("orders", orderService.getAll());
        return "order";
    }

    @GetMapping(value = { "/order/edit" })
    public String mvcOrderEdit(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        List<Order> orderList = orderService.findById(id);
        if (orderList.isEmpty() != true) {
            model.addAttribute("order", orderList.get(0));
            return "order-edit";
        }

        model.addAttribute("orders", orderService.getAll());
        return "order";
    }

    @PostMapping("/order/update")
    public String mvcOrderUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @Valid Order order,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            order.setId(id);
            return "order-edit";
        }

        orderService.updateOrder(order);
        model.addAttribute("orders", orderService.getAll());
        return "order";
    }

    @PostMapping("/order/insert")
    public String mvcOrderInsert(
            @Valid Order order,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "order-edit";
        }

        orderService.createOrder(order);
        model.addAttribute("orders", orderService.getAll());
        return "order";
    }

    @GetMapping("/order/add")
    public String mvcOrderAdd(
            Order order) {
        return "order-add";
    }

    @GetMapping("/order/finished")
    public String mvcOrderFinished(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        orderService.setFinished(id);
        model.addAttribute("orders", orderService.getAll());
        return "order";
    }
}
