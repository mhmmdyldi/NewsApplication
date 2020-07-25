package com.mhmmd.snappnews.utils;

import com.mhmmd.snappnews.data.AppRepository;
import com.mhmmd.snappnews.ui.newsSource.NewsSourceViewModel;
import com.mhmmd.snappnews.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@Singleton
public class ViewModelPorviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppRepository appRepository;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelPorviderFactory(AppRepository appRepository, SchedulerProvider schedulerProvider) {
        this.appRepository = appRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsSourceViewModel.class)){
            return (T) new NewsSourceViewModel(appRepository, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
