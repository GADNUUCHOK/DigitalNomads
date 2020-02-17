package com.example.digitalnomads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class ProgressTaskImage extends AsyncTask<String, Void, Bitmap> {

    Bitmap bitmap;

    public ProgressTaskImage() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        onProgressChanged(mPhotoView, 0);
//        onReceivedTitle(mPhotoView, "Download");
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String urldisplay = url[0];
        bitmap = null;
        try{
            InputStream srt = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(srt);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {

        super.onPostExecute(bitmap);
//        imgV.setImageBitmap(bitmap);
//        onProgressChanged(mPhotoView, 100);
//        onReceivedTitle(mPhotoView, null);
    }
}
