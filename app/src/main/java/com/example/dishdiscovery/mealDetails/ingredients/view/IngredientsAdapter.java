package com.example.dishdiscovery.mealDetails.ingredients.view;

import static com.example.dishdiscovery.network.Api.END_POINTS.INGREDIENT_URL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        holder.bind(ingredients.get(position), measurements.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient, measurement;
        ImageView ingredientImage;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient = itemView.findViewById(R.id.tvIngredientName);
            measurement = itemView.findViewById(R.id.tvIngredientMeasurement);
            ingredientImage = itemView.findViewById(R.id.ivIngredientImage);
        }

        void bind(String ingredient, String measurement) {
            this.ingredient.setText(ingredient);
            this.measurement.setText(measurement);
            Glide.with(itemView.getContext())
                    .load(INGREDIENT_URL + ingredient.replaceAll(" ", "%20").trim() + "-Small.png")
                    .placeholder(R.drawable.icon_ingredient_loading)
                    .error(R.drawable.icon_ingredient_failed)
                    .into(ingredientImage);
        }
    }
}
