package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.models.ApplicationUserRepository;
import com.example.codefellowship.models.Post;
import com.example.codefellowship.models.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;

@Controller
public class PostController
{
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

//    @GetMapping("/users/post")
//    public String getApplicationUserPost()
//    {
//        return "post";
//    }

    // Assume that this new post belongs to the logged-in user
    @PostMapping("/users/post")
    public RedirectView createPost(String body, Principal principal)
    {
        long milliseconds = System.currentTimeMillis();
        Date createdAt = new Date(milliseconds);
        ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        Post post = new Post(body, createdAt, currentUser);
        postRepository.save(post);
        return new RedirectView("/users/" + currentUser.getId());
    }
}
