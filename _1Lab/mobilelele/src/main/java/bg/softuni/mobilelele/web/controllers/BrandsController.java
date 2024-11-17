package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private final OfferService offerService;

    public BrandsController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ModelAndView getAllOffers() {
        ModelAndView modelAndView = new ModelAndView("brands");
        modelAndView.addObject("allBrands", this.offerService.getAllOffersGroupedByBrand());

        return modelAndView;
    }
}
