package ru.mirea.stulovad.mireaproject.ui.browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import ru.mirea.stulovad.mireaproject.R;

public class WebViewFragment extends Fragment {
    private WebView webView;

    public WebViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = view.findViewById(R.id.webView);
        if (webView != null) { // Добавь проверку на всякий случай
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDomStorageEnabled(true); // Добавь эту строку
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://www.mirea.ru");
        }

        return view;
    }
}