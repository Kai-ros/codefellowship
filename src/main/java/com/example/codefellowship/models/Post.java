package com.example.codefellowship.models;

import javafx.geometry.Pos;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(columnDefinition = "text")
    String body;
    Date createdAt;

    @ManyToOne
    ApplicationUser poster;

    public long getId()
    {
        return id;
    }

    public String getBody()
    {
        return body;
    }

    public Date getCreatedAtDate()
    {
        return createdAt;
    }

    public Post() {}

    public Post(String body, Date createdAt, ApplicationUser poster)
    {
        this.body = body;
        this.createdAt = createdAt;
        this.poster = poster;
    }
}
