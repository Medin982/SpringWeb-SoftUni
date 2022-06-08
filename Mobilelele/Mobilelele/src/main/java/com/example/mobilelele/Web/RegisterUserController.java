package com.example.mobilelele.Web;

import com.example.mobilelele.Models.DTO.UserLoginDTO;
import com.example.mobilelele.Models.DTO.UserRegisterDTO;
import com.example.mobilelele.Repository.UserRepository;
import com.example.mobilelele.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterUserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public RegisterUserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {
        this.userService.login(userLoginDTO);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDTO registerDTO) {
        this.userService.registerUser(registerDTO);
        return "redirect:/";
    }


}
