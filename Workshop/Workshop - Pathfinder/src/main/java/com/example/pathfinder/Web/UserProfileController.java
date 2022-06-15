package com.example.pathfinder.Web;

import com.example.pathfinder.Models.User;
import com.example.pathfinder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserProfileController {

    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "loggedUser")
    private User user() {
        return this.userService.getLoggedUser();
    }

    @GetMapping("/users/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/users/profile")
    public String profile(Model model) {
        model.addAttribute(this.userService.getLoggedUser());
        return "profile";

    }
}
