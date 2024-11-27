package com.philately.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface HomeController {

    @GetMapping("/")
    ModelAndView home();
}
