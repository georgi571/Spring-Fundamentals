package com.philately.web.controller.impl;

import com.philately.model.dto.AddStampDTO;
import com.philately.model.enums.PaperType;
import com.philately.util.CurrentUser;
import com.philately.service.StampService;
import com.philately.service.UserService;
import com.philately.web.controller.StampController;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stamps")
public class StampControllerImpl implements StampController {
    private final CurrentUser currentUser;
    private final StampService stampService;
    private final UserService userService;

    public StampControllerImpl(CurrentUser currentUser, StampService stampService, UserService userService) {
        this.currentUser = currentUser;
        this.stampService = stampService;
        this.userService = userService;
    }

    @Override
    public AddStampDTO addStampDTO() {
        return new AddStampDTO();
    }


    @Override
    public ModelAndView newStamp(AddStampDTO addStampDTO) {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("add-stamp");
        modelAndView.addObject("allPaper", PaperType.values());
        return modelAndView;
    }

    @Override
    public ModelAndView createStamp(
            @Valid AddStampDTO addStampDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("stampDTO", addStampDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.stampDTO", bindingResult);
            return new ModelAndView("redirect:/stamps/add");
        }

        this.stampService.createStamp(addStampDTO);

        return new ModelAndView("redirect:/");
    }

    @Override
    public ModelAndView addStampToWishedStamps(@RequestParam("stampId") Long stampId) {
        this.userService.addWishedStamp(stampId);

        return new ModelAndView("redirect:/");
    }

    @Override
    public String deleteStampFromWishedStamps(@RequestParam("stampId") Long stampId) {
        this.userService.removeWishedStamp(stampId);

        return "redirect:/";
    }

    @Override
    public ModelAndView buyStampsFromWishedStamps() {
        this.userService.buyStamps();

        return new ModelAndView("redirect:/");
    }
}
