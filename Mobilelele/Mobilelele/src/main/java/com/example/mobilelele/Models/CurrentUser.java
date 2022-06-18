package com.example.mobilelele.Models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String email;

    private String name;
    private boolean isLogged;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogged() {
        return this.isLogged;
    }

    public boolean isLogout() {
        return !isLogged();
    }

    public void setLogged(boolean logged) {
        this.isLogged = logged;
    }

    public void clear() {
        this.isLogged = false;
        this.name = null;
        this.email = null;
    }
}
