package com.example.battleship.web;

import com.example.battleship.models.dtos.AddShipDTO;
import com.example.battleship.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipsController {

    private final ShipService shipService;

    @Autowired
    public ShipsController(ShipService shipService) {
        this.shipService = shipService;
    }

    @ModelAttribute("addShip")
    public AddShipDTO initDTO() {
        return new AddShipDTO();
    }

    @GetMapping("/ships/add")
    public String addShip() {
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String addShip(@Valid AddShipDTO addShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addShip", addShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShip", bindingResult);
            return "/redirect:/ships/add";
        }
        return "redirect:/home";
    }
}
