package com.example.springpracticev2.controllers;

import com.example.springpracticev2.services.LoggedUserManagementService;
import com.example.springpracticev2.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/main")
    public String getMainPage(@RequestParam(required = false) String logout, Model model) {
        if(logout != null)
            loggedUserManagementService.setUsername(null);

        String username = loggedUserManagementService.getUsername();
        int count = loginCountService.getCount();

        if(username == null)
            return "redirect:/";

        model.addAttribute("username", username);
        model.addAttribute("count", count);
        return "main";
    }
}
