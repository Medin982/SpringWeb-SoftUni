package com.example.mobilelele.Web;

import com.example.mobilelele.Models.DTO.AddOfferDTO;
import com.example.mobilelele.Models.DTO.OfferDTO;
import com.example.mobilelele.Services.BrandService;
import com.example.mobilelele.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }
    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }

    @GetMapping("/all")
    public String allOffer(Model model) {
        if (!model.containsAttribute("allOffers")) {
            model.addAttribute("allOffers", new OfferDTO());
        }
        model.addAttribute("allOffers", this.offerService.getAllOffers());
        return "offers";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO",
                    bindingResult);
            return "redirect:add";
        }
        this.offerService.addOffer(addOfferDTO);
        return "redirect:all";
    }
}
