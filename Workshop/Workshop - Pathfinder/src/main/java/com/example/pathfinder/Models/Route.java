package com.example.pathfinder.Models;

import com.example.pathfinder.Models.Enums.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String name;

    private String videoUrl;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "route", targetEntity = Picture.class)
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "route", targetEntity = Comment.class, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToMany
    private Set<Category> categories;
    
    public Route() {
        this.pictures = new HashSet<>();
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
