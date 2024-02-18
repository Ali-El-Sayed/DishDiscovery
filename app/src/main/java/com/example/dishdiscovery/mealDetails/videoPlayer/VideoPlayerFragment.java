package com.example.dishdiscovery.mealDetails.videoPlayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.databinding.FragmentVideoPlayerBinding;
import com.example.dishdiscovery.model.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class VideoPlayerFragment extends Fragment {

    private final Meal _meal;
    private FragmentVideoPlayerBinding _binding;

    public VideoPlayerFragment(Meal meal) {
        this._meal = meal;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getLifecycle().addObserver(_binding.youtubePlayerView);


        _binding.youtubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = _meal.strYoutube.split("=")[1];
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        _binding.btnOpenSource.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_meal.strSource));
            startActivity(browserIntent);
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _binding.youtubePlayerView.release();
    }
}