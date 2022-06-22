package com.example.battleship.web;

import com.example.battleship.models.dtos.ALLShipsDTO;
import com.example.battleship.models.dtos.ShipDTO;
import com.example.battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("allShips")
    public ALLShipsDTO initDTO() {
        ALLShipsDTO ships = new ALLShipsDTO();
        ships.setShips(this.userService.getAllShips());
        return ships;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
