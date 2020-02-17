package com.example.digitalnomads.Model.ListNews;

import android.graphics.Bitmap;

public interface NewsRowView {

    void setURL(String url);

    void setTitle(String title);

    void setImage(Bitmap image);

    void setDate(String date);

    void setDescription(String description);
}
