package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.model.dtos.inputs.UserLoginDTO;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public LoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("loginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public ModelAndView viewLogin(UserLoginDTO userLoginDTO) {
        if (currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("auth-login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView doLogin(
            UserLoginDTO userLoginDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        boolean isCorrect = this.userService.loginUser(userLoginDTO);
        boolean isBanned = false;

        if (isCorrect) {
            isBanned = this.userService.isBanned(userLoginDTO);

            System.out.println("correct %n");

            if (isBanned) {
                redirectAttributes.addFlashAttribute("userIsBanned", "You have been banned!");
                System.out.println("banned %n");
            }
        } else {
            redirectAttributes.addFlashAttribute("wrongUsernameOrPassword", "Username or password is incorrect");
            System.out.println("wrong %n");

        }

        if (!isCorrect || isBanned) {
            System.out.println("return %n");

            redirectAttributes.addFlashAttribute("loginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);
            return new ModelAndView("redirect:/users/login");
        }

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        this.userService.logout();

        return modelAndView;
    }
}
