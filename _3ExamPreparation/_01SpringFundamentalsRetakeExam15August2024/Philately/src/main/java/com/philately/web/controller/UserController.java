package com.philately.web.controller;

import com.philately.model.dto.UserLoginDTO;
import com.philately.model.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
public interface UserController {
    @GetMapping("/login")
    ModelAndView viewLogin(UserLoginDTO userLoginDTO);

    @PostMapping("/login")
    ModelAndView doLogin(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @GetMapping("/register")
    ModelAndView viewRegister(UserRegisterDTO userRegisterDTO);

    @PostMapping("/register")
    ModelAndView doRegister(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    @PostMapping("/logout")
    ModelAndView logout();
}
