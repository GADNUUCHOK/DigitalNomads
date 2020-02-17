package com.example.digitalnomads.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.digitalnomads.MainActivity;

public class DataBaseListNews extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "News.db";
    private static final String TABLE_NAME = "News_Article_table";

    private static final String URL = "URL";
    private static final String TITLE = "TITLE";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String DATE = "DATE";
    private static final String URL_IMAGE = "URL_IMAGE";



    public DataBaseListNews(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "URL TEXT, TITLE TEXT, DESCRIPTION TEXT, DATE TEXT, URL_IMAGE BLOB)");
        Log.d(MainActivity.TAG, "CreateDB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d(MainActivity.TAG, "UpgradeDB");
    }

    public boolean insertData(String url, String title, String description, String date, byte[] urlImage) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(URL, url);
        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(DATE, date);
        contentValues.put(URL_IMAGE, urlImage);

        long result = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();

        return result != -1;
    }

    public void update(String url, String title, String description, String date, byte[] urlImage, int i) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentUpdateValues = new ContentValues();
        contentUpdateValues.put(URL, url);
        contentUpdateValues.put(TITLE, title);
        contentUpdateValues.put(DESCRIPTION, description);
        contentUpdateValues.put(DATE, date);
        contentUpdateValues.put(URL_IMAGE, urlImage);
        String where = "ID=" + i;
        sqLiteDatabase.update(TABLE_NAME, contentUpdateValues, where, null);
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
    }

}
