package com.example.dishdiscovery.weeklyMeals.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.weeklyMeals.model.WeeklyMeal;

import java.util.List;

public class WeeklyMealsAdapter extends RecyclerView.Adapter<WeeklyMealsAdapter.ViewHolder> {

    private final List<WeeklyMeal> meals;
    private final OnWeeklyMealClickListener listener;

    public WeeklyMealsAdapter(List<WeeklyMeal> meals, OnWeeklyMealClickListener listener) {
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WeeklyMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), R.layout.meal_of_the_day_card, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyMealsAdapter.ViewHolder holder, int position) {
        holder.itemView.findViewById(R.id.tvMealArea).setVisibility(View.GONE);
        holder.bind(meals.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeek, mealName;
        ImageView mealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.meal_of_the_day);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealImage = itemView.findViewById(R.id.ivMealImage);
        }

        public void bind(WeeklyMeal meal, OnWeeklyMealClickListener listener) {
            dayOfWeek.setText(meal.getDayOfWeek());
            mealName.setText(meal.getMealName());
            Glide.with(itemView.getContext()).load(meal.getMealThumb()).placeholder(R.drawable.icon_ingredient_loading).error(R.drawable.icon_ingredient_failed).into(mealImage);
            itemView.setOnClickListener(v -> listener.onWeeklyMealClick(meal.getMealId()));
        }
    }
}
