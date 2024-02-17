package com.example.dishdiscovery.model;

import java.util.List;

public class UserFavMeals {
    private String userId;
    private List<String> favMeals;

    public UserFavMeals() {
    }

    public UserFavMeals(String userId, List<String> favMeals) {
        this.userId = userId;
        this.favMeals = favMeals;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getFavMeals() {
        return favMeals;
    }

    @Override
    public String toString() {
        return "UserFavMeals{" +
                "userId='" + userId + '\'' +
                ", favMeals=" + favMeals +
                '}';
    }
}
