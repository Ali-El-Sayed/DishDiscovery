package com.example.dishdiscovery.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.AllMeals.view.OnMealClickListener;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.model.Meal;

import java.util.List;

public class MealSearchAdapter extends RecyclerView.Adapter<MealSearchAdapter.AllMealsViewHolder> {
    private final List<Meal> allMeals;
    private final OnMealClickListener _onMealClickListener;


    public MealSearchAdapter(List<Meal> allMeals, OnMealClickListener onMealClickListener) {
        this.allMeals = allMeals;
        _onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public AllMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_of_the_day_card, parent, false);
        return new AllMealsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMealsViewHolder holder, int position) {
        holder.bind(allMeals.get(position));
        holder.itemView.setOnClickListener(v -> _onMealClickListener.onMealClick(allMeals.get(position).idMeal));
    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }

    public class AllMealsViewHolder extends RecyclerView.ViewHolder {
        TextView mealName, mealArea;
        ImageView mealImage;

        public AllMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealImage = itemView.findViewById(R.id.ivMealImage);
            mealArea = itemView.findViewById(R.id.tvMealArea);
            itemView.findViewById(R.id.meal_of_the_day).setVisibility(View.GONE);
        }

        void bind(Meal meal) {
            mealName.setText(meal.strMeal);
            mealArea.setText(meal.strArea);
            Glide.with(itemView.getContext()).load(meal.strMealThumb + "/preview").into(mealImage);
        }
    }
}
