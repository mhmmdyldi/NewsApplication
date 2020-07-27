package com.mhmmd.snappnews.ui.newsArticles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhmmd.snappnews.R;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.ui.base.BaseViewHolder;
import com.mhmmd.snappnews.ui.newsSource.NewsSourceAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsArticleAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<HeadlineEntity> mHeadlineList;

    public NewsArticleAdapter(List<HeadlineEntity> mHeadlineList) {
        this.mHeadlineList = mHeadlineList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsArticleAdapter.NewsArticleViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_article_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return mHeadlineList.size();
    }

    public void addItems(List<HeadlineEntity> headlineList) {
        mHeadlineList.clear();
        mHeadlineList.addAll(headlineList);
        notifyDataSetChanged();
    }

    class NewsArticleViewHolder extends BaseViewHolder {

        private TextView articleTitle, publishedAt;
        private ImageView articleImage;

        public NewsArticleViewHolder(View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.news_article_title);
            publishedAt = itemView.findViewById(R.id.news_published_at);
            articleImage = itemView.findViewById(R.id.news_article_image);
        }

        @Override
        public void onBind(int position) {
            final HeadlineEntity article = mHeadlineList.get(position);
            if (article.title != null)
                articleTitle.setText(article.title);
            if (article.publishedAt != null)
                publishedAt.setText(article.publishedAt);
            if (article.urlToImage != null)
                Glide.with(itemView.getContext())
                        .load(article.urlToImage)
                        .into(articleImage);
        }
    }

}
