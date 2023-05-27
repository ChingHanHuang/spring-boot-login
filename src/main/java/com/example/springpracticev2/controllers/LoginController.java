package com.example.springpracticev2.controllers;

import com.example.springpracticev2.processors.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;

    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        loginProcessor.setName(name);
        loginProcessor.setPassword(password);

        boolean loggedIn = loginProcessor.login();

        if(loggedIn)
            return "redirect:/main";
        else
            model.addAttribute("message", "Login failed.");
        return "login";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String Hello() {
        return "Hello";
    }
}
