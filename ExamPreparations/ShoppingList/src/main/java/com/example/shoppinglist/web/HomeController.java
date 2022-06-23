package com.example.shoppinglist.web;

import com.example.shoppinglist.models.enums.CategoryName;
import com.example.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("totalSum", this.productService.getTotalSum());
        model.addAttribute("food", this.productService.getProducts(CategoryName.FOOD));
        model.addAttribute("drink", this.productService.getProducts(CategoryName.DRINK));
        model.addAttribute("houseHold", this.productService.getProducts(CategoryName.HOUSEHOLD));
        model.addAttribute("other", this.productService.getProducts(CategoryName.OTHER));
        return "home";
    }
}
