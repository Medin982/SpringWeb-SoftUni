package com.example.spotifyplaylistapplication.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
