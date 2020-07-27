package com.mhmmd.snappnews.ui.newsArticles;

import android.os.Bundle;

import com.mhmmd.snappnews.R;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.utils.AppConstants;
import com.mhmmd.snappnews.utils.Resource;
import com.mhmmd.snappnews.utils.ViewModelPorviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;

public class NewsArticlesActivity extends AppCompatActivity {

    @Inject
    ViewModelPorviderFactory factory;
    private NewsArticleViewModel newsArticleViewModel;
    private RecyclerView recyclerView;
    private NewsArticleAdapter newsArticleAdapter;
    private LinearLayoutManager mLayoutManager;
    private String newsSourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_articles);
        AndroidInjection.inject(this);
        getExtras();
        setupUIelements();
        setupViewModel();
        setupObserver();
        loadNewsFeedArticles();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            newsSourceId = extras.getString(AppConstants.SOURC_ID_BUNDLE_KEY);
        }
    }

    private void setupUIelements() {
        recyclerView = findViewById(R.id.news_article_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        newsArticleAdapter = new NewsArticleAdapter(new ArrayList<>());
    }

    private void setupViewModel(){
        newsArticleViewModel = new ViewModelProvider(this, factory).get(NewsArticleViewModel.class);
    }

    private void setupObserver(){
        newsArticleViewModel.getArticles()
                .observe(this, new Observer<Resource<List<HeadlineEntity>>>() {
                    @Override
                    public void onChanged(Resource<List<HeadlineEntity>> resource) {
                        switch (resource.getStatus()){
                            case SUCCESS:
                                newsArticleAdapter.addItems(resource.getData());
                                recyclerView.setAdapter(newsArticleAdapter);
                                break;
                            case ERROR:
                                break;
                            case LOADING:

                        }
                    }
                });
    }

    private void loadNewsFeedArticles() {
        newsArticleViewModel.loadNewsArticles(newsSourceId);
    }
}
