package com.example.digitalnomads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    public WebView webViewNews;
    public static final String URL = "com.example.digitalnomads.url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webViewNews = findViewById(R.id.wv_page_news);
        String urlWebView = getIntent().getStringExtra("url");
        Log.d(MainActivity.TAG, "WebView URL " + urlWebView);
        setPageNews(webViewNews);

    }

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(URL, url);
        return intent;
    }

    public void setPageNews(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra(URL);
        webView.loadUrl(url);
        Log.d(MainActivity.TAG, "WebView URL " + url);
    }
}
