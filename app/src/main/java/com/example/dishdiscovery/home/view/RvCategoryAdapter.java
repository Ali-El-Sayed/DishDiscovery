package com.example.dishdiscovery.home.view;

import android.view.LayoutInflater;
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

    private final OnCardItemClick _onCardItemClick;
    Meal meal;
    List<Category> categories;

    public RvCategoryAdapter(OnCardItemClick onCardItemClick, Meal meal, List<Category> categories) {
        this.meal = meal;
        this.categories = categories;
        this._onCardItemClick = onCardItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_of_the_day_card, parent, false);
            return new MealOfTheDayViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
            return new CategoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((MealOfTheDayViewHolder) holder).bindData(meal);
            holder.itemView.setOnClickListener(v -> _onCardItemClick.onMealOfTheDayClick(meal.idMeal));
        } else {
            ((CategoryViewHolder) holder).bindData(categories.get(position - 1));
            holder.itemView.setOnClickListener(v -> _onCardItemClick.onCategoryClick(categories.get(position - 1).getStrCategory()));
        }
    }

    @Override
    public int getItemCount() {
        return categories.size() + 1;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
        notifyDataSetChanged();
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

        public void bindData(Category category) {
            tvCategoryName.setText(category.getStrCategory());
            Glide.with(ivCategoryImage.getContext()).load(category.getStrCategoryThumb()).into(ivCategoryImage);
        }

    }

    public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder {

        TextView tvMealName, tvCountry;
        ImageView ivMealImage;

        public MealOfTheDayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            tvCountry = itemView.findViewById(R.id.tvMealArea);
            ivMealImage = itemView.findViewById(R.id.ivMealImage);
        }

        public void bindData(Meal meal) {
            tvMealName.setText(meal.strMeal);
            tvCountry.setText(meal.strArea);
            Glide.with(ivMealImage.getContext()).load(meal.strMealThumb).into(ivMealImage);
        }
    }
}
