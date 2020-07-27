package com.mhmmd.snappnews.ui.newsArticles;

import android.util.Log;

import com.mhmmd.snappnews.data.AppRepository;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.utils.AppConstants;
import com.mhmmd.snappnews.utils.Resource;
import com.mhmmd.snappnews.utils.Status;
import com.mhmmd.snappnews.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class NewsArticleViewModel extends ViewModel {
    private final AppRepository mAppRepository;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<Resource<List<HeadlineEntity>>> articles;
    private String newsSourceId;

    public NewsArticleViewModel(AppRepository mAppRepository, SchedulerProvider mSchedulerProvider) {
        this.mAppRepository = mAppRepository;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.articles = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    public MutableLiveData<Resource<List<HeadlineEntity>>> getArticles() {
        return articles;
    }

    public void loadNewsArticles(String newsSourceId) {
        this.newsSourceId = newsSourceId;
        loadCachedArticlesFromLocalDb();
    }

    private void loadCachedArticlesFromLocalDb() {
        mCompositeDisposable.add(mAppRepository.getArticlesOfSourceFromDb(newsSourceId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(articlesList -> {
                    if (articlesList.size() > 0) {
                        articles.postValue(new Resource<>(Status.SUCCESS, articlesList, null));
                    }
                    setupSyncArticlesInTimeIntervals();
                }, throwable -> {

                })
        );
    }

    private void setupSyncArticlesInTimeIntervals() {
        mCompositeDisposable.add(Observable
                .interval(AppConstants.SYNC_DELAY, AppConstants.SYNC_TIME_INTERVALS, TimeUnit.MINUTES)
                .observeOn(mSchedulerProvider.ui())
                .subscribe(l -> {
                            Log.d("SnapppNewsss", "setupSyncArticlesInTimeIntervals: " + l);
                            loadNewsHeadlinesFromApi();
                        }
                )
        );
    }

    private void loadNewsHeadlinesFromApi() {
        mCompositeDisposable.add(
                mAppRepository.getArticlesListApiCall(newsSourceId)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(articlesList -> {
//                            saveArticlesInLocalDb(articlesList);
                            deletArticlesFromLocalDb(articlesList);
                        }, throwable -> {

                        })
        );
    }

    private void deletArticlesFromLocalDb(List<HeadlineEntity> articlesList) {
        mCompositeDisposable.add(mAppRepository.deleteArticlesOfSourceFromDB(newsSourceId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(() -> {
                    saveArticlesInLocalDb(articlesList);
                }, throwable -> {

                })
        );
    }

    private void saveArticlesInLocalDb(List<HeadlineEntity> articlesList) {
        mCompositeDisposable.add(mAppRepository.saveHeadlineListInDb(articlesList)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(() -> {
                    loadArticlesFromLocalDB();
                }, throwable -> {

                })
        );
    }

    private void loadArticlesFromLocalDB() {
        mCompositeDisposable.add(mAppRepository.getArticlesOfSourceFromDb(newsSourceId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(articlesList -> {
                    if (articlesList.size() > 0) {
                        articles.postValue(new Resource<>(Status.SUCCESS, articlesList, null));
                    }
                }, throwable -> {

                })
        );
    }

}
