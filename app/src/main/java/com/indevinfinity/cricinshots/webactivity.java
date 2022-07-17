package com.indevinfinity.cricinshots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webactivity extends AppCompatActivity {
WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webactivity);

        wv1 = findViewById(R.id.wv1);

        WebSettings webSettings = wv1.getSettings();
        wv1.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        wv1.loadUrl("https://codersera.tech/news/");


    }
}
