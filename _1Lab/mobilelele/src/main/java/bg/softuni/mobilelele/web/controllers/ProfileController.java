package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.model.dtos.inputs.UserRegistrationDTO;
import bg.softuni.mobilelele.model.dtos.outputs.UserProfileDTO;
import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.UserRole;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public ProfileController(UserService userService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userDTO")
    public UserProfileDTO userProfileDTO() {
        return new UserProfileDTO();
    }

    @GetMapping("/profile")
    public ModelAndView showUserProfile() {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        User user = this.userService.getUserByUsername(this.currentUser.getUsername()).get();

        UserProfileDTO userProfileDTO = this.modelMapper.map(user, UserProfileDTO.class);

        ModelAndView modelAndView = new ModelAndView("user-profile");
        modelAndView.addObject("userProfileDTO", userProfileDTO);

        return modelAndView;
    }
}
