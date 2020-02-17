package com.example.digitalnomads.Model.ListNews;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.digitalnomads.MainActivity;
import com.example.digitalnomads.Model.NewsObject;
import com.example.digitalnomads.WebViewActivity;

import java.util.List;

public class NewsListPresenter {

    private final List<NewsObject> newsObjectList;
    private WebViewActivity webViewActivity;
    private MainActivity mainActivity;


    public NewsListPresenter(List<NewsObject> newsObjectList, MainActivity mainActivity) {
        this.newsObjectList = newsObjectList;
        this.mainActivity = mainActivity;
    }


    public void onBindViewAtPosition(int i, ListHolder listHolder) {
        NewsObject newsObject = newsObjectList.get(i);
        listHolder.setURL(newsObject.getmUrlNews());
        listHolder.setTitle(newsObject.getmNameNews());
        listHolder.setDate(newsObject.getmDateNews());
        listHolder.setDescription(newsObject.getmDescriptionNews());
        listHolder.setImage(newsObject.getmImageNews());
    }

    public int getCount() {
        return newsObjectList.size();
    }

    public void onClickItemPosition(int i) {
        String url = newsObjectList.get(i).getmUrlNews();
        Log.d(MainActivity.TAG, "URL " + url);
        mainActivity.goToWebViewActivity(url);
//        WebView browser = findViewById(R.id.webview);
//        browser.setWebViewClient(new WebViewClient());
//        browser.loadUrl(url);
    }
}
