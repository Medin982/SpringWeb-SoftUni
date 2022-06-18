package com.example.mobilelele.Models.DTO;

import com.example.mobilelele.Config.CustomValidation.IsMatched;
import com.example.mobilelele.Config.CustomValidation.UniqueEmailValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@IsMatched(
        first = "password",
        second = "confirmPassword")
public class UserRegisterDTO {
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotEmpty(message = "Email should be provided.")
    @Email(message = "Email should be valid.")
    @UniqueEmailValidation
    private String email;

    @NotEmpty
    @Size(min = 5)
    private String password;

    @NotBlank
    private String confirmPassword;

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
