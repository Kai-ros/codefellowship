package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class ApplicationUserController
{
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String fullName, Date dateOfBirth, String bio)
    {
        ApplicationUser newUser = new ApplicationUser(username,
                // bcrypt handles hashing/salting
                encoder.encode(password),
                fullName,
                dateOfBirth,
                bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/users/{id}")
    public String getIndividualUserPage(@PathVariable long id, Model model)
    {
        ApplicationUser individualUser = applicationUserRepository.findById(id).get();
        model.addAttribute("individualUser", individualUser);
        return "individualUser";
    }

    @GetMapping("/myprofile")
    public String getCurrentUserProfile(Principal principal, Model model)
    {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        model.addAttribute("individualUser", currentUser);
        return "individualUser";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }
}










