package com.example.pathfinder.Web;

import com.example.pathfinder.Models.DTO.UserLoginDTO;
import com.example.pathfinder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "userDTO")
    private UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        boolean isLogged = this.userService.login(userLoginDTO);
        if (isLogged) {
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("userDTO", userLoginDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
