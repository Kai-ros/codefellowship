package com.example.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String getRoot(Principal principal, Model model)
    {
        model.addAttribute("user", principal);
        return "root";
    }

    @GetMapping("/signup")
    public String getSignup()
    {
        return "registration";
    }

    @GetMapping("/error")
    public String getErrorPage()
    {
        return "error";
    }

}

