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
    public String getIndividualUserPage(@PathVariable long id, Model model, Principal principal)
    {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        ApplicationUser individualUser = applicationUserRepository.findById(id).get();
        model.addAttribute("individualUser", individualUser);
        model.addAttribute("userID", currentUser.getId());
        model.addAttribute("username", currentUser.getUsername());
        return "individualUser";
    }

    @GetMapping("/myprofile")
    public String getCurrentUserProfile(Principal principal, Model model)
    {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        model.addAttribute("individualUser", currentUser);
        model.addAttribute("userID", currentUser.getId());
        model.addAttribute("username", currentUser.getUsername());
        return "individualUser";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followAUser(@PathVariable long id, Principal principal)
    {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser individualUser = applicationUserRepository.findById(id).get();
        currentUser.addFollowers(individualUser);
        applicationUserRepository.save(currentUser);
        return new RedirectView("/users/" + id);
    }

    @GetMapping("/feed")
    public String getUsersThatIFollow(Principal principal, Model model)
    {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        model.addAttribute("currentUser", applicationUserRepository.findByUsername(principal.getName()));
        model.addAttribute("username", currentUser.getUsername());
        return "feed";
    }

    @GetMapping("/users")
    public String displayUsers(Principal principal, Model model) {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        model.addAttribute("currentUser", applicationUserRepository.findByUsername(principal.getName()));
        model.addAttribute("allUsers", applicationUserRepository.findAll());
        model.addAttribute("username", currentUser.getUsername());
        return "users";
    }
}










