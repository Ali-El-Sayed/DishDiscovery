package com.example.dishdiscovery.favorite.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.model.UserLocalFavMeals;

import java.util.List;

public class RvFavoriteAdapter extends RecyclerView.Adapter<RvFavoriteAdapter.FavoriteViewHolder> {
    private final List<UserLocalFavMeals> _userLocalFavMeals;
    private final OnFavMealClickListener _onMealClickListener;

    public RvFavoriteAdapter(List<UserLocalFavMeals> userLocalFavMeals, OnFavMealClickListener onMealClickListener) {
        this._userLocalFavMeals = userLocalFavMeals;
        this._onMealClickListener = onMealClickListener;

    }

    @NonNull
    @Override
    public RvFavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_of_the_day_card, parent, false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvFavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.bind(_userLocalFavMeals.get(position), _onMealClickListener);
    }

    @Override
    public int getItemCount() {
        return _userLocalFavMeals.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView mealName, mealArea;
        ImageView mealImage;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tvMealName);
            mealArea = itemView.findViewById(R.id.tvMealArea);
            mealImage = itemView.findViewById(R.id.ivMealImage);
            itemView.findViewById(R.id.meal_of_the_day).setVisibility(View.GONE);
        }


        public void bind(UserLocalFavMeals userLocalFavMeals, OnFavMealClickListener onMealClickListener) {
            mealName.setText(userLocalFavMeals.strMeal);
            mealArea.setText(userLocalFavMeals.strArea);
            itemView.setOnClickListener(v -> onMealClickListener.onFavMealClick(userLocalFavMeals.idMeal));
        }

    }
}
