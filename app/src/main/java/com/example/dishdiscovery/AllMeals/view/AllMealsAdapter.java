package com.example.dishdiscovery.AllMeals.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.FilteredMeal;

import java.util.List;

public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.AllMealsViewHolder> {
    private final List<FilteredMeal> allMeals;
   private final OnMealClickListener _onMealClickListener;


    public AllMealsAdapter(List<FilteredMeal> allMeals, OnMealClickListener onMealClickListener) {
        this.allMeals = allMeals;
        _onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public AllMealsAdapter.AllMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_of_the_day_card, parent, false);

        return new AllMealsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMealsAdapter.AllMealsViewHolder holder, int position) {
        holder.bind(allMeals.get(position));
        holder.itemView.setOnClickListener(v -> _onMealClickListener.onMealClick(allMeals.get(position).getIdMeal()));
    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }

    public class AllMealsViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;

        public AllMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealImage = itemView.findViewById(R.id.ivMealImage);
            itemView.findViewById(R.id.tvMealArea).setVisibility(View.GONE);
            itemView.findViewById(R.id.meal_of_the_day).setVisibility(View.GONE);
        }

        void bind(FilteredMeal filterResponse) {
            mealName.setText(filterResponse.getStrMeal());
            Glide.with(itemView.getContext()).load(filterResponse.getStrMealThumb()+"/preview").into(mealImage);
        }
    }
}
