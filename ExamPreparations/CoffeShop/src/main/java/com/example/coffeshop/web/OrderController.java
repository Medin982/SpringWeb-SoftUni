package com.example.coffeshop.web;

import com.example.coffeshop.models.dtos.AddOrderDTO;
import com.example.coffeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        if (!model.containsAttribute("addOrderDTO")) {
            model.addAttribute("addOrderDTO", new AddOrderDTO());
        }
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid AddOrderDTO addOrderDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", bindingResult);
            return "redirect:add";
        }
        this.orderService.addOrder(addOrderDTO);
        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable long id) {
        this.orderService.removeOrder(id);
        return "redirect:/";
    }
}
