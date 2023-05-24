package com.example.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.About_Activity);

        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/about.html");
    }
}
