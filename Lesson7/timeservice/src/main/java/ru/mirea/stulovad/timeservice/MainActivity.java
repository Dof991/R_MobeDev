package ru.mirea.stulovad.timeservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.stulovad.timeservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private final String host = "time.nist.gov";
    private final int port = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(() -> {
                    String result = null;
                    try {
                        Socket socket = new Socket(host, port);
                        BufferedReader reader = SocketUtils.getReader(socket);
                        reader.readLine(); // skip first blank line
                        result = reader.readLine();
                        socket.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Error connecting to time server", e);
                    }

                    String finalResult = result;
                    handler.post(() -> {
                        if (finalResult != null) {
                            String[] arr = finalResult.split(" ");
                            if (arr.length > 2) {
                                binding.textView.setText(getString(R.string.date_time_format, arr[1], arr[2]));
                            } else {
                                binding.textView.setText(getString(R.string.unexpected_format));
                                Log.w(TAG, "Unexpected format: " + finalResult);
                            }
                        } else {
                            binding.textView.setText(getString(R.string.error_get_time));
                        }
                    });
                });
            }
        });
    }

}