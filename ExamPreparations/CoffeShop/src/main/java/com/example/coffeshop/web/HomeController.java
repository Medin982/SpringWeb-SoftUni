package com.example.coffeshop.web;

import com.example.coffeshop.models.enums.CategoryName;
import com.example.coffeshop.service.OrderService;
import com.example.coffeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    public HomeController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("allOrders", this.orderService.getAllOrders());
        model.addAttribute("neededTime", this.orderService.getPrepareTimeForAllOrders());
        model.addAttribute("employee", this.userService.getAllEmployee());
        return "home";
    }
}
