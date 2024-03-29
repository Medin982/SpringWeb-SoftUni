package com.example.coffeshop.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDTO {

    @Size(min = 5, max = 20,
            message = "The length of the username must be between 5 and 20 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotNull
    private String firstName;

    @Size(min = 5, max = 20,
            message = "The length of the username must be between 5 and 20 characters")
    @NotBlank(message = "Username cannot be empty")
    private String lastName;

    @Email(message = "Enter valid email address.")
    @NotBlank
    private String email;

    @Size(min = 3, message = "Password length must be more than 3 characters long.")
    @NotBlank
    private String password;

    @Size(min = 3, message = "Password length must be more than 3 characters long.")
    @NotBlank
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
