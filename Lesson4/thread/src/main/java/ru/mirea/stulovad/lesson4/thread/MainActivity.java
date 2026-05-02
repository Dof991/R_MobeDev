package ru.mirea.stulovad.lesson4.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;



public	class	MainActivity extends AppCompatActivity	{

    private	int	counter	= 0;

    @Override
    protected void onCreate(Bundle	savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("---Запущен поток № %d студентом группы	№ %s номер по списку № %d ---", numberThread, "БСБО-09-23", 16));
                        binding.tv.setText(String.format("---Запущен поток № %d студентом группы	№ %s номер по списку № %d ---", numberThread, "БСБО-09-23", 16));
                        long endTime = System.currentTimeMillis() + 2 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("---ThreadProject", "Выполнен поток № ---" + numberThread);
                            binding.tv.setText("Выполнен поток № ---" + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}


