package com.example.spotifyplaylistapplication.models.dtos;

import com.example.spotifyplaylistapplication.models.enums.StyleType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddSongDTO {

    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters")
    @NotBlank(message = "Performer cannot by empty")
    private String performer;

    @Size(min = 2, max = 20, message = "Performer name length must be between 3 and 20 characters")
    @NotBlank(message = "Performer cannot by empty")
    private String title;

    @PastOrPresent(message = "The Date that cannot be in the future")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive(message = "The duration must be a positive number")
    @NotNull
    private Double duration;

    @NotNull(message = "You must select a style")
    private StyleType style;

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public StyleType getStyle() {
        return style;
    }

    public void setStyle(StyleType style) {
        this.style = style;
    }
}
