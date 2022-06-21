package com.example.mobilelele.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

<<<<<<< HEAD
    private final OfferService offerService;
    private final BrandService brandService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

=======
>>>>>>> parent of cb4b52d (Work on the OfferController.)
    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }
<<<<<<< HEAD

    @GetMapping("/all")
    public String allOffer() {
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
            return "redirect:/offers/add";
        }
        this.offerService.addOffer(addOfferDTO);
        return "redirect:/offers/all";
    }
=======
>>>>>>> parent of cb4b52d (Work on the OfferController.)
}
