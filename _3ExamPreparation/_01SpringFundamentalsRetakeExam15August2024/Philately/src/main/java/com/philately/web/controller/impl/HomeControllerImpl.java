package com.philately.web.controller.impl;

import com.philately.model.dto.BoughtStampsDTO;
import com.philately.model.dto.MyStampsDTO;
import com.philately.model.dto.MyWishedStampDTO;
import com.philately.model.dto.OfferedStampDTO;
import com.philately.util.CurrentUser;
import com.philately.service.StampService;
import com.philately.service.UserService;
import com.philately.web.controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class HomeControllerImpl implements HomeController {

    private final CurrentUser currentUser;
    private final UserService userService;
    private final StampService stampService;

    public HomeControllerImpl(CurrentUser currentUser, UserService userService, StampService stampService) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.stampService = stampService;
    }

    @Override
    public ModelAndView home() {
        if (!currentUser.isLoggedIn()) {
            return new ModelAndView("index");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        Set<MyStampsDTO> ownedStamps = this.userService.getOwnedStamps();
        List<OfferedStampDTO> offeredStamp = this.stampService.getAllStampsWhichIsNotFromOwner(this.currentUser);
        Set<MyWishedStampDTO> wishedStamps = this.userService.getWishedStamps();
        Set<BoughtStampsDTO> boughtStamps = this.userService.getBoughtStamps();
        modelAndView.addObject("ownedStamps", ownedStamps);
        modelAndView.addObject("offeredStamp", offeredStamp);
        modelAndView.addObject("wishedStamps", wishedStamps);
        modelAndView.addObject("boughtStamps", boughtStamps);
        return modelAndView;
    }
}
