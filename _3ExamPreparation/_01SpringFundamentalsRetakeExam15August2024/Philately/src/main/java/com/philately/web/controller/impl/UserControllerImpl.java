package com.philately.web.controller.impl;

import com.philately.model.dto.UserLoginDTO;
import com.philately.model.dto.UserRegisterDTO;
import com.philately.util.CurrentUser;
import com.philately.service.UserService;
import com.philately.web.controller.UserController;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserControllerImpl(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("loginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @Override
    public ModelAndView viewLogin(UserLoginDTO userLoginDTO) {
        if (currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }

    @Override
    public ModelAndView doLogin(
            @Valid UserLoginDTO userLoginDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        boolean isCorrect = this.userService.loginUser(userLoginDTO);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            return new ModelAndView("redirect:/users/login");
        }

        if (!isCorrect) {
            redirectAttributes.addFlashAttribute("loginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("wrongUsernameOrPassword", "Incorrect username or password!");
            return new ModelAndView("redirect:/users/login");
        }

        return new ModelAndView("redirect:/");
    }

    @Override
    public ModelAndView viewRegister(UserRegisterDTO userRegisterDTO) {
        if (currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("register");
    }

    @Override
    public ModelAndView doRegister(
            @Valid UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ModelAndView("redirect:/users/register");
        }

        this.userService.registerUser(userRegisterDTO);
        return new ModelAndView("redirect:/users/login");
    }

    @Override
    public ModelAndView logout() {
        this.userService.logOut();

        return new ModelAndView("redirect:/");
    }
}
