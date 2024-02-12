package com.example.dishdiscovery.home.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;

import java.util.List;

public class RvCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Meal meal;
    List<Category> categories;

    public RvCategoryAdapter(Meal meal, List<Category> categories) {
        this.meal = meal;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = View.inflate(parent.getContext(), R.layout.meal_of_the_day_card, null);
            return new MealOfTheDayViewHolder(view);
        } else {
            View view = View.inflate(parent.getContext(), R.layout.category_card, null);
            return new CategoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            MealOfTheDayViewHolder mealOfTheDayViewHolder = (MealOfTheDayViewHolder) holder;
            mealOfTheDayViewHolder.tvMealName.setText(meal.getStrMeal());
            mealOfTheDayViewHolder.tvCountry.setText(meal.getStrArea());
            Glide.with(mealOfTheDayViewHolder.ivMealImage.getContext()).load(meal.getStrMealThumb()).into(mealOfTheDayViewHolder.ivMealImage);
        } else {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            categoryViewHolder.tvCategoryName.setText(categories.get(position - 1).getStrCategory());
            Glide.with(categoryViewHolder.ivCategoryImage.getContext()).load(categories.get(position - 1).getStrCategoryThumb()).into(categoryViewHolder.ivCategoryImage);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else
            return 1;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName;
        ImageView ivCategoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);
        }
    }

    public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder {

        TextView tvMealName, tvCountry;
        ImageView ivMealImage;

        public MealOfTheDayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            ivMealImage = itemView.findViewById(R.id.ivMealImage);
        }
    }
}
