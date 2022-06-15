package com.example.pathfinder.Models.DTO;

import javax.validation.constraints.*;

public class UserRegistrationDTO {
    @Size(min = 5, max = 20)
    @NotBlank
    private String username;

    @Size(min = 5, max = 20)
    @NotBlank
    private String fullName;

    @Email
    private String email;

    @Positive
    private int age;

    @Size(min = 5, max = 20)
    @NotBlank
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
