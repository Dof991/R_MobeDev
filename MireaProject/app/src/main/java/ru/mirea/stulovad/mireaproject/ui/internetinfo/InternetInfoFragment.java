package ru.mirea.stulovad.mireaproject.ui.internetinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.stulovad.mireaproject.databinding.FragmentInternetInfoBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetInfoFragment extends Fragment {
    private final String TAG = "InternetInfo";
    private FragmentInternetInfoBinding binding;

    public InternetInfoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInternetInfoBinding.inflate(inflater, container, false);
        binding.button.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                new DownloadImageTask().execute("https://cataas.com/cat");
            } else {
                Toast.makeText(requireContext(), "Нет интернета", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;

        android.net.Network network = connectivityManager.getActiveNetwork();
        if (network == null) return false;
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        return capabilities != null && (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET));
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                binding.textView.setText("Загружаем котика...");
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                return downloadImage(urls[0]);
            } catch (IOException e) {
                Log.e("DownloadImageTask", "Error downloading image", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (binding == null) return;

            if (result != null) {
                binding.imageView.setImageBitmap(result);
                binding.textView.setText("Котик загружен!");
            } else {
                binding.textView.setText("Ошибка при загрузке");
            }
        }

        private Bitmap downloadImage(String address) throws IOException {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (InputStream inputStream = connection.getInputStream()) {
                        return BitmapFactory.decodeStream(inputStream);
                    }
                } else {
                    Log.e("DownloadImageTask", "Response code: " + responseCode);
                    return null;
                }
            } finally {
                connection.disconnect();
            }
        }
    }
}
