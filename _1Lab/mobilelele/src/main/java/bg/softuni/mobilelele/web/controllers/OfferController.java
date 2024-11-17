package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.model.dtos.inputs.AddOfferDTO;
import bg.softuni.mobilelele.model.dtos.inputs.UpdateOfferDTO;
import bg.softuni.mobilelele.model.dtos.outputs.OfferDetailsDTO;
import bg.softuni.mobilelele.model.entities.Offer;
import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.model.enums.EngineType;
import bg.softuni.mobilelele.model.enums.ModelType;
import bg.softuni.mobilelele.model.enums.TransmissionType;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.OfferService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("offerDTO")
    public AddOfferDTO addOfferDTO() {
        return new AddOfferDTO();
    }

    @GetMapping("/add")
    public ModelAndView newOffer(AddOfferDTO addOfferDTO) {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("offer-add");

        modelAndView.addObject("allEngineTypes", EngineType.values());
        modelAndView.addObject("allModelType", ModelType.values());
        modelAndView.addObject("allBrandType", BrandType.values());
        modelAndView.addObject("allTransmissionType", TransmissionType.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView createOffer(
            @Valid AddOfferDTO addOfferDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);
            return new ModelAndView("redirect:/offers/add");
        }

        long newOfferId = offerService.createOffer(addOfferDTO);
        return new ModelAndView("redirect:/offers/" + newOfferId);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showUpdateOffer(@PathVariable("id") long id) {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        Optional<Offer> existingOffer = this.offerService.getOfferById(id);

        if (existingOffer.isEmpty()) {
            return new ModelAndView("redirect:/");
        }

        boolean isAllowedToUpdate = Objects.equals(existingOffer.get().getUser().getUsername(), this.currentUser.getUsername());

        if (this.currentUser.isAdmin()) {
            isAllowedToUpdate = true;
        }

        if (!isAllowedToUpdate) {
            return new ModelAndView("redirect:/");
        }

        UpdateOfferDTO updateOfferDTO = this.modelMapper.map(existingOffer.get(), UpdateOfferDTO.class);

        ModelAndView modelAndView = new ModelAndView("update");

        modelAndView.addObject("allEngineTypes", EngineType.values());
        modelAndView.addObject("allModelType", ModelType.values());
        modelAndView.addObject("allBrandType", BrandType.values());
        modelAndView.addObject("allTransmissionType", TransmissionType.values());

        modelAndView.addObject("updateOfferDTO", updateOfferDTO);
        return modelAndView;
    }


    @PutMapping("/{id}/edit")
    public ModelAndView doUpdateOffer(@PathVariable("id") long id,
                                    @Valid UpdateOfferDTO updateOfferDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateOfferDTO", updateOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferDTO", bindingResult);
            return new ModelAndView("redirect:/offers/" + id + "/edit");
        }

        this.offerService.updateOffer(id, updateOfferDTO);

        return new ModelAndView("redirect:/offers/" + id);
    }

    @GetMapping("/{id}")
    public ModelAndView showOfferDetails(@PathVariable("id") Long id) {
        Optional<Offer> existingOffer = this.offerService.getOfferById(id);

        if (existingOffer.isEmpty()) {
            return new ModelAndView("redirect:/");
        }

        boolean isAllowedToUpdate = Objects.equals(existingOffer.get().getUser().getUsername(), this.currentUser.getUsername());

        if (this.currentUser.isAdmin()) {
            isAllowedToUpdate = true;
        }

        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("offerDetails", offerService.getOfferDetails(id));
        modelAndView.addObject("isAllowedToUpdate", isAllowedToUpdate);

        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {
        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
