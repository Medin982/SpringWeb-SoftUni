package com.example.mobilelele.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandsController {

    @GetMapping("/brands/all")
    public String allBrands() {
        return "brands";
    }
}
