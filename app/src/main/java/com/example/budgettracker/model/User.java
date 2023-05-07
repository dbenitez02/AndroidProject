package com.example.budgettracker.model;

import androidx.annotation.NonNull;

public class User {

    private long userID;
    private String userName;
    private String userEmail;
    private float userBudget;
    private String userPassword;

    public User(@NonNull long id) {
        userID = id;
    }

    public long getUserId() {
        return userID;
    }

    public void setId(long id) {
        userID = id;
    }

    public void setName(String name) {
        userName = name;
    }

    public void setEmail(String email) {
        userEmail = email;
    }

    public void setBudget(float budget) {
        userBudget = budget;
    }

    public void setPassword(String password) {
        userPassword = password;
    }


}
