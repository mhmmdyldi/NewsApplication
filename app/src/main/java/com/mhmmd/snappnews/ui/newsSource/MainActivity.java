package com.mhmmd.snappnews.ui.newsSource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;

import android.os.Bundle;
import android.util.Log;

import com.mhmmd.snappnews.R;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.utils.Resource;
import com.mhmmd.snappnews.utils.ViewModelPorviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelPorviderFactory factory;
    private NewsSourceViewModel newsSourceViewModel;
    private RecyclerView recyclerView;
    private NewsSourceAdapter newsSourceAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        setupUIelements();
        setupViewModel();
        setupObserver();
        loadNewsFeedSource();
    }

    private void setupUIelements() {
        recyclerView = findViewById(R.id.news_source_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        newsSourceAdapter = new NewsSourceAdapter(new ArrayList<>());
    }

    private void setupViewModel(){
        newsSourceViewModel = new ViewModelProvider(this, factory).get(NewsSourceViewModel.class);
    }

    private void setupObserver(){
        newsSourceViewModel.getSources()
                .observe(this, new Observer<Resource<List<SourceEntity>>>() {
                    @Override
                    public void onChanged(Resource<List<SourceEntity>> resource) {
                        Log.d("SnappNews", "onChanged: ");
                        newsSourceAdapter.addItems(resource.getData());
                        recyclerView.setAdapter(newsSourceAdapter);
                    }
                });
    }

    private void loadNewsFeedSource() {
        newsSourceViewModel.loadNewsSources();
    }
}
