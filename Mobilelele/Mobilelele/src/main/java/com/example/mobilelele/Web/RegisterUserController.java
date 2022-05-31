package com.example.mobilelele.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterUserController {

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

}
