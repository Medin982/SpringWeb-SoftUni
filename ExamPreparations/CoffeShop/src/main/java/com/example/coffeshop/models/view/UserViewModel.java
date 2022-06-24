package com.example.coffeshop.models.view;

public class UserViewModel {

    private long id;

    private String username;

    private int orderCount;

    public UserViewModel(long id, String username, int orderCount) {
        this.id = id;
        this.username = username;
        this.orderCount = orderCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
