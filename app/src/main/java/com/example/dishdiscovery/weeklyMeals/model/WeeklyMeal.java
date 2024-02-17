package com.example.dishdiscovery.weeklyMeals.model;

public class WeeklyMeal {
    private String dayOfWeek;
    private String mealId;

    private String userId;

    private String mealName;

    private String mealThumb;


    public WeeklyMeal(String dayOfWeek, String mealId, String userId, String mealName, String mealThumb) {
        this.dayOfWeek = dayOfWeek;
        this.mealId = mealId;
        this.userId = userId;
        this.mealName = mealName;
        this.mealThumb = mealThumb;
    }

    public WeeklyMeal() {
    }





    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealThumb() {
        return mealThumb;
    }

    public void setMealThumb(String mealThumb) {
        this.mealThumb = mealThumb;
    }


    @Override
    public String toString() {
        return "WeeklyMeal{" + "dayOfWeek='" + dayOfWeek + '\'' + ", mealId='" + mealId + '\'' + ", userId='" + userId + '\'' + ", mealName='" + mealName + '\'' + ", mealThumb='" + mealThumb + '\'' + '}';
    }

}
