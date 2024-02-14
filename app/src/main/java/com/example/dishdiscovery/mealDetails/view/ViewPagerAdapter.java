package com.example.dishdiscovery.mealDetails.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dishdiscovery.mealDetails.ingredients.view.IngredientFragment;
import com.example.dishdiscovery.mealDetails.instructions.InstructionsFragment;
import com.example.dishdiscovery.mealDetails.videoPlayer.VideoPlayerFragment;
import com.example.dishdiscovery.model.Meal;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private Meal meal = new Meal();

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new IngredientFragment(meal);
            case 1:
                return new InstructionsFragment(meal);
            case 2:
                return new VideoPlayerFragment(meal);
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
