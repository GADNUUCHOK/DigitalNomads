package com.example.digitalnomads.Model.ListNews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalnomads.MainActivity;
import com.example.digitalnomads.Model.NewsObject;
import com.example.digitalnomads.WebViewActivity;

import java.util.List;

/**
 *
 */
public class ListAdapter
        extends RecyclerView.Adapter<ListHolder> {

    private final NewsListPresenter newsListPresenter;
//    List<NewsObject> mNewsList;
//    MainActivity mainActivity;

    /**
     *
     * @param newsList
     */
    public ListAdapter(NewsListPresenter newsList) {
        newsListPresenter = newsList;
    }

    /**
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ListHolder(layoutInflater, viewGroup, newsListPresenter);
    }

    /**
     *
     * @param listHolder
     * @param i
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        newsListPresenter.onBindViewAtPosition(i, listHolder);

//        NewsObject newsObject = mNewsList.get(i);
//        listHolder.bind(newsObject);
//        listHolder.mName.setText(newsObject.getmNameNews());
//        listHolder.mDate.setText(newsObject.getmDateNews());
//        listHolder.mDescription.setText(newsObject.getmDescriptionNews());
//        listHolder.mPicture.setImageBitmap(newsObject.getmImageNews());
//        listHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentChangeBrigadier = new Intent(mainActivity, WebViewActivity.class);// Создание намерения для изменения бригадира
//                mainActivity.goToWebViewActivity(intentChangeBrigadier(ListHolder listHolder));
//                listHolder.
//            }
//        });
    }

    /**
     * Метод возвращает размер списка
     * @return возвращает размер списка
     */
    @Override
    public int getItemCount() {
        return newsListPresenter.getCount();
    }
}
