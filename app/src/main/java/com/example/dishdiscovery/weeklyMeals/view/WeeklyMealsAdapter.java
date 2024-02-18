package com.example.dishdiscovery.weeklyMeals.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.Meal;

import java.util.List;

public class WeeklyMealsAdapter extends RecyclerView.Adapter<WeeklyMealsAdapter.ViewHolder> {

    private final List<Meal> meals;
    private final OnWeeklyMealClickListener listener;

    public WeeklyMealsAdapter(List<Meal> meals, OnWeeklyMealClickListener listener) {
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WeeklyMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_of_the_day_card, parent, false);
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
        TextView dayOfWeek, mealName, mealArea;
        ImageView mealImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.meal_of_the_day);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealImage = itemView.findViewById(R.id.ivMealImage);
            mealArea = itemView.findViewById(R.id.tvMealArea);
        }

        public void bind(Meal meal, OnWeeklyMealClickListener listener) {
            dayOfWeek.setText(meal.dayOfTheWeek);
            mealName.setText(meal.strMeal);
            mealArea.setText(meal.strArea);
            Glide.with(itemView.getContext()).load(meal.strMealThumb).placeholder(R.drawable.icon_ingredient_loading).error(R.drawable.icon_ingredient_failed).into(mealImage);
            itemView.setOnClickListener(v -> listener.onWeeklyMealClick(meal.idMeal));
        }
    }
}
