package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ModelAndView getAllOffers() {
        ModelAndView modelAndView = new ModelAndView("offers");
        modelAndView.addObject("allOffers", this.offerService.getAllOffers());

        return modelAndView;
    }
}
