package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.model.dtos.inputs.UserRegistrationDTO;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.UserRole;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public RegistrationController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("registerDTO")
    public UserRegistrationDTO registerDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public ModelAndView viewRegister(UserRegistrationDTO userRegisterDTO) {
        if (currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("auth-register");
        modelAndView.addObject("allRoles", UserRole.values());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doRegister(
            @Valid UserRegistrationDTO userRegisterDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        Optional<User> userByEmail = this.userService.getUserByEmail(userRegisterDTO.getEmail());
        Optional<User> userByUsername = this.userService.getUserByUsername(userRegisterDTO.getUsername());

        if (userByEmail.isPresent()) {
            redirectAttributes.addFlashAttribute("emailExistsError", "This email is already in use.");
        }

        if (userByUsername.isPresent()) {
            redirectAttributes.addFlashAttribute("usernameExistsError", "This username is already in use.");
        }

        if (bindingResult.hasErrors() || userByEmail.isPresent() || userByUsername.isPresent()) {
            redirectAttributes.addFlashAttribute("registerDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return new ModelAndView("redirect:/users/register");
        }

        this.userService.registerUser(userRegisterDTO);
        return new ModelAndView("redirect:/users/login");
    }
}

