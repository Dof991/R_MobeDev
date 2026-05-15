package ru.mirea.stulovad.lesson4.musicapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.stulovad.lesson4.musicapp.databinding.ActivityMainBinding;

public	class	MainActivity extends AppCompatActivity	{
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle	savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.artistName.setText("Король И Шут");
                binding.trackName.setText("Лесник");
            }
        });
    }
}