package com.example.mobilelele.Web;

import com.example.mobilelele.Models.DTO.AddOfferDTO;
import com.example.mobilelele.Models.DTO.UpdateOfferDTO;
import com.example.mobilelele.Services.BrandService;
import com.example.mobilelele.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("addOfferDTO")
    public AddOfferDTO initDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("brands", this.brandService.getAllBrands());
        return "offer-add";
    }

    @GetMapping("/all")
    public String allOffer(Model model) {
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

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("offers", this.offerService.getDetailsOffers(id));
        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateOffer(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("updateOffer")) {
            model.addAttribute("updateOffer", new AddOfferDTO());
        }
        model.addAttribute("offer", this.offerService.getDetailsOffers(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateOffer(@PathVariable Long id, @Valid AddOfferDTO addOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateOffer", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOffer", bindingResult);
            return "redirect:update";
        }
        this.offerService.updateOffer(addOfferDTO, id);
        return "redirect:all";
    }
}
