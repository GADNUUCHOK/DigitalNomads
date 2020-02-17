package com.example.digitalnomads.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 *
 */
public class NewsObject {

    private String mUrlNews;
    private Bitmap mImageNews;
    private String mNameNews;
    private String mDescriptionNews;
    private String mDateNews;

    /**
     *
     *
     * @param mNameNews
     * @param mDescriptionNews
     * @param mDateNews
     */
    public NewsObject(String urlNews, Bitmap mImageNews, String mNameNews, String mDescriptionNews, String mDateNews) {
        this.mUrlNews = urlNews;
        this.mImageNews = mImageNews;
        this.mNameNews = mNameNews;
        this.mDescriptionNews = mDescriptionNews;
        this.mDateNews = mDateNews;
    }

    public String getmUrlNews() {
        return mUrlNews;
    }

    public void setmUrlNews(String mUrlNews) {
        this.mUrlNews = mUrlNews;
    }

    public Bitmap getmImageNews() {
        return mImageNews;
    }

    public void setmImageNews(Bitmap mImageNews) {
        this.mImageNews = mImageNews;
    }

    public String getmNameNews() {
        return mNameNews;
    }

    public void setmNameNews(String mNameNews) {
        this.mNameNews = mNameNews;
    }

    public String getmDescriptionNews() {
        return mDescriptionNews;
    }

    public void setmDescriptionNews(String mDescriptionNews) {
        this.mDescriptionNews = mDescriptionNews;
    }

    public String getmDateNews() {
        return mDateNews;
    }

    public void setmDateNews(String mDateNews) {
        this.mDateNews = mDateNews;
    }
}
