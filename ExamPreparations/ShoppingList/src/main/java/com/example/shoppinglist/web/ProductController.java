package com.example.shoppinglist.web;

import com.example.shoppinglist.models.dtos.ProductDTO;
import com.example.shoppinglist.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        if (!model.containsAttribute("productDTO")) {
            model.addAttribute("productDTO", new ProductDTO());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productDTO", productDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDTO", bindingResult);
            return "redirect:add";
        }
        this.productService.addProduct(productDTO);
        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable String id) {
        this.productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAllProduct() {
        this.productService.buyAll();
        return "redirect:/";
    }
}
