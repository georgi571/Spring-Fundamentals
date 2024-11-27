package com.philately.web.controller;

import com.philately.model.dto.AddStampDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/stamps")
public interface StampController {

    @ModelAttribute("stampDTO")
    AddStampDTO addStampDTO();

    @GetMapping("/add")
    ModelAndView newStamp(AddStampDTO addStampDTO);

    @PostMapping("/add")
    ModelAndView createStamp(@Valid AddStampDTO addStampDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @PostMapping("add/wish")
    ModelAndView addStampToWishedStamps(@RequestParam("stampId") Long stampId);

    @DeleteMapping("remove/wish")
    String deleteStampFromWishedStamps(@RequestParam("stampId") Long stampId);

    @PostMapping("buy/wish")
    ModelAndView buyStampsFromWishedStamps();
}
