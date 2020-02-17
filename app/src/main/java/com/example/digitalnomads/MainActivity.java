package com.example.digitalnomads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.digitalnomads.DataBase.ComponentData;
import com.example.digitalnomads.DataBase.ConverterImageBitmapToByte;
import com.example.digitalnomads.DataBase.DataBaseListNews;
import com.example.digitalnomads.Model.ListNews.ListAdapter;
import com.example.digitalnomads.Model.ListNews.NewsListPresenter;
import com.example.digitalnomads.Model.NewsObject;
import com.example.digitalnomads.Network.NewsApi;
import com.example.digitalnomads.Network.NewsObjectResponse;
import com.example.digitalnomads.View.ListNewsView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class MainActivity
        extends AppCompatActivity
        implements ListNewsView {

    public static final String TAG = "LogTag";


    RecyclerView mRecyclerNews;
    ListAdapter mRecyclerAdapter;
    DataBaseListNews mDataBaseListNews;
    List<NewsObject> newsObjectList = new ArrayList<>();
    ProgressBar mProgressBar;
    ComponentData componentData;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //componentData =
        mRecyclerNews = findViewById(R.id.rv_list_news);
        mProgressBar = findViewById(R.id.pb_loading);

        mDataBaseListNews = new DataBaseListNews(this.getApplicationContext());

        ConnectivityManager cm = (ConnectivityManager) this.getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            NewsApi newsApi = retrofit.create(NewsApi.class);

            Call<NewsObjectResponse> listObject = newsApi.getData("android", "2019-04-00", "mDate", "26eddb253e7840f988aec61f2ece2907", 1);

            listObject.enqueue(new Callback<NewsObjectResponse>() {
                @Override
                public void onResponse(Call<NewsObjectResponse> call, Response<NewsObjectResponse> response) {
                    assert response.body() != null;
                    if (mDataBaseListNews.getData().getCount() == 0) {
                        for (int i = 0; i < response.body().getArticles().size(); i++) {
                            String url = response.body().getArticles().get(i).getUrl();
                            String urlImage = response.body().getArticles().get(i).getmUrlImage();
                            String title = response.body().getArticles().get(i).getmTitle();
                            String description = response.body().getArticles().get(i).getmDescription();
                            String date = response.body().getArticles().get(i).getmDate();
                            addParams(url, urlImage, title, description, date);
                            mDataBaseListNews.insertData(url, title, description, date, ConverterImageBitmapToByte.getBytes(getBitmapUrl(urlImage)));
                            //Log.d(TAG, "Create URL DataBase" + mDataBaseListNews.getData().getString(i));
                        }
                    } else {

                        for (int i = 0; i < response.body().getArticles().size(); i++) {
                            String url = response.body().getArticles().get(i).getUrl();
                            String urlImage = response.body().getArticles().get(i).getmUrlImage();
                            String title = response.body().getArticles().get(i).getmTitle();
                            String description = response.body().getArticles().get(i).getmDescription();
                            String date = response.body().getArticles().get(i).getmDate();
                            addParams(url, urlImage, title, description, date);
                            mDataBaseListNews.update(url, title, description, date, ConverterImageBitmapToByte.getBytes(getBitmapUrl(urlImage)), i+1);
                            //Log.d(TAG, "Update URL DataBase" + mDataBaseListNews.getData().getString(i));
                        }
                    }


                    mRecyclerAdapter.notifyDataSetChanged();
                    mProgressBar.setVisibility(View.GONE);

                    Cursor cursor = mDataBaseListNews.getData();

                    Log.d(TAG, "getData DataBase" + mDataBaseListNews.getData().getCount());
                    while (cursor.moveToNext()) {
                        String stringURL = cursor.getString(1);
                        Log.d(TAG, "URL " + stringURL);
                        String stringTitle = cursor.getString(2);
                        Log.d(TAG, "Title " + stringTitle);
                        String stringDescription = cursor.getString(3);
                        Log.d(TAG, "Description " + stringDescription);
                        String stringData = cursor.getString(4);
                        Log.d(TAG, "Data " + stringData);
                        byte[] stringImageURL = cursor.getBlob(5);
                        Log.d(TAG, "Number " + cursor.getInt(0));
                    }
                }

                @Override
                public void onFailure(Call<NewsObjectResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure " + t);
                }
            });

            mRecyclerAdapter = new ListAdapter(new NewsListPresenter(newsObjectList, this));
            mRecyclerNews.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerNews.setAdapter(mRecyclerAdapter);
        } else {
            Log.d(TAG, "Connect with DataBase");
            setListNews();
            mRecyclerAdapter.notifyDataSetChanged();
            mProgressBar.setVisibility(View.GONE);
        }

    }

    /**
     *
     */
    @Override
    public void showInternetDisconnect() {

    }

    public Bitmap getBitmapUrl(String stringImageURL) {
        ProgressTaskImage progressTaskImage = new ProgressTaskImage();
        Bitmap bitmap = null;
        try {
            bitmap = progressTaskImage.execute(stringImageURL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void goToWebViewActivity(String url) {
        Intent intent = WebViewActivity.newIntent(MainActivity.this, url);
        startActivity(intent);
    }

    public void addParams(String stringURL, String stringImageURL, String stringTitle, String stringDescription, String stringData) {

        ProgressTaskImage progressTaskImage = new ProgressTaskImage();
        Bitmap bitmap = null;
        try {
            bitmap = progressTaskImage.execute(stringImageURL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NewsObject newsObject = new NewsObject(stringURL ,bitmap, stringTitle, stringDescription, stringData);
        Log.d(TAG, "Bitmap " + progressTaskImage.bitmap);
        newsObjectList.add(newsObject);
    }

    public void setListNews() {
        Cursor cursor = mDataBaseListNews.getData();
        Log.d(TAG, "getData DataBase");
//        cursor.getString(1);
//        Log.d(TAG, "URL " + cursor.getString(1));
//        int i = 0;
        while (cursor.moveToNext()) {
            String stringURL = cursor.getString(1);
            Log.d(TAG, "URL " + stringURL);
            String stringTitle = cursor.getString(2);
            Log.d(TAG, "Title " + stringTitle);
            String stringDescription = cursor.getString(3);
            Log.d(TAG, "Description " + stringDescription);
            String stringData = cursor.getString(4);
            Log.d(TAG, "Data " + stringData);
            byte[] stringImageURL = cursor.getBlob(5);
//            Log.d(TAG, "URL Image " + stringImageURL);
            //Uri uri = Uri.parse(stringImageURL);
            Bitmap bitmap = ConverterImageBitmapToByte.getImage(stringImageURL);

//            ProgressTaskImage progressTaskImage = new ProgressTaskImage();
//            Bitmap bitmap = null;
//            try {
//                bitmap = progressTaskImage.execute(stringImageURL).get();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            NewsObject newsObject = new NewsObject(stringURL, bitmap, stringTitle, stringDescription, stringData);
//            Log.d(TAG, "Bitmap " + progressTaskImage.bitmap);
            newsObjectList.add(newsObject);

        }
        mRecyclerAdapter = new ListAdapter(new NewsListPresenter(newsObjectList, this));
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerNews.setAdapter(mRecyclerAdapter);
    }
}
