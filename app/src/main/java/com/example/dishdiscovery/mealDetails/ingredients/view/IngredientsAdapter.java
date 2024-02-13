package com.example.dishdiscovery.mealDetails.ingredients.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dishdiscovery.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {
    List<String> ingredients;
    List<String> measurements;

    public IngredientsAdapter(List<String> ingredients, List<String> measurements) {
        this.ingredients = ingredients;
        this.measurements = measurements;
    }

    @NonNull
    @Override
    public IngredientsAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_card, parent, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientViewHolder holder, int position) {
        holder.ingredient.setText(ingredients.get(position));
        holder.measurement.setText(measurements.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient, measurement;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient = itemView.findViewById(R.id.tvIngredientName);
            measurement = itemView.findViewById(R.id.tvIngredientMeasurement);
        }
    }
}
