package ru.mirea.stulovad.intentapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.stulovad.intentapp.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySecondBinding binding;
    TextView tvForTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTimeForString();


    }

    protected void setTimeForString (){
        Intent intent = getIntent();
        String timeString = intent.getStringExtra("timeString");
        int myNumber=24;

        tvForTime = findViewById(R.id.tvForData);
        tvForTime.setText(String.format("КВАДРАТ ЗНАЧЕНИЯ" +
                "МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ %d, а текущее " +
                "время %s", myNumber*myNumber,timeString));
    }

}