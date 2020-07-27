package com.mhmmd.snappnews.ui.newsSource;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhmmd.snappnews.R;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.ui.base.BaseViewHolder;
import com.mhmmd.snappnews.ui.newsArticles.NewsArticlesActivity;
import com.mhmmd.snappnews.utils.AppConstants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsSourceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<SourceEntity> mSourceList;

    public NewsSourceAdapter(List<SourceEntity> sourceList) {
        this.mSourceList = sourceList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsSourceViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_source_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return mSourceList.size();
    }

    public void addItems(List<SourceEntity> sourceList) {
        mSourceList.addAll(sourceList);
        notifyDataSetChanged();
    }

    class NewsSourceViewHolder extends BaseViewHolder {

        private TextView newsSourceName, newsSourceCategory;

        public NewsSourceViewHolder(View itemView) {
            super(itemView);
            newsSourceName = itemView.findViewById(R.id.news_source_name);
            newsSourceCategory = itemView.findViewById(R.id.news_source_category);
        }

        @Override
        public void onBind(int position) {
            SourceEntity newsSource = mSourceList.get(position);
            if (newsSource.name != null)
                newsSourceName.setText(newsSource.name);
            if (newsSource.category != null)
                newsSourceCategory.setText(newsSource.category);

            itemView.setOnClickListener(view -> {
                Intent launchActivityIntent = new Intent(view.getContext(), NewsArticlesActivity.class);
                launchActivityIntent.putExtra(AppConstants.SOURC_ID_BUNDLE_KEY, newsSource.id);
                view.getContext().startActivity(launchActivityIntent);
            });
        }
    }
}
