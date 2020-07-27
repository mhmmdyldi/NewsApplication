package com.mhmmd.snappnews.di.module;

import com.mhmmd.snappnews.ui.newsArticles.NewsArticlesActivity;
import com.mhmmd.snappnews.ui.newsSource.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract NewsArticlesActivity bindNewsArticlesActivity();
}
