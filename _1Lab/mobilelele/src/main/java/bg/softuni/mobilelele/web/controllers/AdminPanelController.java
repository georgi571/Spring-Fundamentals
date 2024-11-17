package bg.softuni.mobilelele.web.controllers;

import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.service.CurrentUser;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    private final UserService userService;
    private final CurrentUser currentUser;


    public AdminPanelController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/panel")
    public ModelAndView viewPanel() {
        if (!currentUser.isAdmin()) {
            return new ModelAndView("redirect:/");
        }

        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        List<User> users = this.userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("admin-panel");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @PutMapping("/users/{id}/make/admin")
    public ModelAndView addUserAsAdmin(@PathVariable("id") long id) {
        if (!currentUser.isAdmin()) {
            return new ModelAndView("redirect:/");
        }

        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        this.userService.setUserAsAdmin(id);

        return new ModelAndView("redirect:/admin/panel");
    }

    @PutMapping("/users/{id}/make/user")
    public ModelAndView addAdminAsUser(@PathVariable("id") long id) {
        if (!currentUser.isAdmin()) {
            return new ModelAndView("redirect:/");
        }

        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        this.userService.setAdminAsUser(id);

        return new ModelAndView("redirect:/admin/panel");
    }

    @PutMapping("/users/{id}/make/ban")
    public ModelAndView banUser(@PathVariable("id") long id) {
        if (!currentUser.isAdmin()) {
            return new ModelAndView("redirect:/");
        }

        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        this.userService.setBanUser(id);

        return new ModelAndView("redirect:/admin/panel");
    }

    @PutMapping("/users/{id}/make/unban")
    public ModelAndView unbanUser(@PathVariable("id") long id) {
        if (!currentUser.isAdmin()) {
            return new ModelAndView("redirect:/");
        }

        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("redirect:/");
        }

        this.userService.setUnbanUser(id);

        return new ModelAndView("redirect:/admin/panel");
    }
}
