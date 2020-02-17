package com.example.digitalnomads.Model.ListNews;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalnomads.Model.NewsObject;
import com.example.digitalnomads.R;

/**
 *
 */
public class ListHolder
        extends RecyclerView.ViewHolder
        implements NewsRowView, View.OnClickListener {

    TextView mName, mDate, mDescription;
    ImageView mPicture;
    NewsObject mNewsObject;
    NewsListPresenter mNewsListPresenter;

    /**
     * Конструктор для отображения элемента
     * @param inflater область элемента
     * @param parent родительский элемент
     */
    ListHolder(LayoutInflater inflater, ViewGroup parent, NewsListPresenter newsListPresenter) {
        super(inflater.inflate(R.layout.item_list_news, parent, false));
        mNewsListPresenter = newsListPresenter;
        mPicture = itemView.findViewById(R.id.iv_picture_news);
        mName = itemView.findViewById(R.id.tv_name_news);
        mDate = itemView.findViewById(R.id.tv_date_news);
        mDescription = itemView.findViewById(R.id.tv_description_news);
        itemView.setOnClickListener(this);

    }

    /**
     *
     * @param newsObject
     */
    public void bind(NewsObject newsObject) {
        mName.setText(newsObject.getmNameNews());
        mNewsObject = newsObject;
    }

    @Override
    public void setURL(String url) {
    }

    @Override
    public void setTitle(String title) {
        mName.setText(title);
    }

    @Override
    public void setImage(Bitmap image) {
        mPicture.setImageBitmap(image);
    }

    @Override
    public void setDate(String date) {
        mDate.setText(date);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void onClick(View v) {
        if (mNewsListPresenter != null) {
            mNewsListPresenter.onClickItemPosition(getAdapterPosition());
        }
    }
}
