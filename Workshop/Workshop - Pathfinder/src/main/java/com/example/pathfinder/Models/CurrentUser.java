package com.example.pathfinder.Models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String username;

    private boolean isLogged;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogout() {
        return !isLogged();
    }

    public void clear() {
        this.isLogged = false;
        this.username = null;
    }
}
