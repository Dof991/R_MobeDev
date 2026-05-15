package ru.mirea.stulovad.lesson4.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.stulovad.lesson4.looper.databinding.ActivityMainBinding;

public class MainActivity extends	AppCompatActivity{
    @Override
    protected void onCreate(Bundle	savedInstanceState)	{
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler = new	Handler(Looper.getMainLooper())	{
            @Override
            public void handleMessage(Message msg)	{
                Log.d(MainActivity.class.getSimpleName(), "---Task execute. This is result: "	+ msg.getData().getString("result---"));
            }
        };
        MyLooper myLooper =	new	MyLooper(mainThreadHandler);
        myLooper.start();

        AgeLooper ageLooper =	new	AgeLooper(mainThreadHandler);
        ageLooper.start();

        binding.editTextMirea.setText("Мой номер по списку №24");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", binding.editTextMirea.getText().toString());
                msg.setData(bundle);
                if (myLooper.mHandler != null) {
                    myLooper.mHandler.sendMessage(msg);
                }
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", binding.editTextMirea.getText().toString());
                bundle.putString("AGE", binding.editTextMirea1.getText().toString());
                msg.setData(bundle);
                if (ageLooper.mHandler != null) {
                    ageLooper.mHandler.sendMessage(msg);
                }
            }
        });
    }
}