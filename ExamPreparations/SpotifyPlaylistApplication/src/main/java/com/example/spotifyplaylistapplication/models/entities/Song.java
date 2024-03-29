package com.example.spotifyplaylistapplication.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
