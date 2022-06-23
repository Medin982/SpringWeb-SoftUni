package com.example.battleship.web;

import com.example.battleship.models.dtos.HomeDTO;
import com.example.battleship.models.session.LoggedUser;
import com.example.battleship.services.ShipService;
import com.example.battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;

    private final LoggedUser loggedUser;

    private final ShipService shipService;

    @Autowired
    public HomeController(UserService userService, LoggedUser loggedUser, ShipService shipService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @ModelAttribute("homeDTO")
    public HomeDTO initDTO() {
        return new HomeDTO();
    }

    @PostMapping("/home")
    public String home(@Valid HomeDTO homeDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeDTO", homeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeDTO", bindingResult);
            return "redirect:/";
        }

        this.shipService.fight(homeDTO);
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (this.loggedUser.getId() == null) {
            return "index";
        }
        if (!model.containsAttribute("homeDTO")) {
            model.addAttribute("homeDTO", new HomeDTO());
        }
        model.addAttribute("attacker", this.shipService.findAllById(this.loggedUser.getId()));
        model.addAttribute("defender", this.shipService.findAllByOtherId(this.loggedUser.getId()));
        model.addAttribute("allShips", this.shipService.findAll());
        return "home";
    }
}
