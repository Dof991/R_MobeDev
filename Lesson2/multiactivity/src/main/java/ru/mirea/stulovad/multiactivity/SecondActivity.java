package ru.mirea.stulovad.multiactivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import ru.mirea.stulovad.multiactivity.databinding.ActivitySecond2Binding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecond2Binding binding;
    private TextView textViewForInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecond2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textViewForInput = findViewById(R.id.tvForInput);

        String text = (String) getIntent().getSerializableExtra("inputedText");
        textViewForInput.setText(text);
    }

}