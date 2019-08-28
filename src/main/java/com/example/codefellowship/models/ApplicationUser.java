package com.example.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    public String fullName;
    public Date dateOfBirth;
    public String bio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "poster")
    public List<Post> posts;

    public ApplicationUser() {}

    public ApplicationUser(String username, String password, String fullName, Date dateOfBirth, String bio)
    {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }


    public List<Post> getPosts()
    {
        return this.posts;
    }

    public void setPosts(ArrayList<Post> postsList)
    {
        this.posts = postsList;
    }

    public void addPosts(Post post)
    {
        this.posts.add(post);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}


