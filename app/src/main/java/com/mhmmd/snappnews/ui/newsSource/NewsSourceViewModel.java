package com.mhmmd.snappnews.ui.newsSource;

import android.util.Log;

import com.mhmmd.snappnews.data.AppRepository;
import com.mhmmd.snappnews.data.model.api.HeadLineReponse;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsSourceViewModel extends ViewModel {
    private final AppRepository mAppRepository;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<List<SourceEntity>> sources;


    public NewsSourceViewModel(AppRepository mAppRepository, SchedulerProvider mSchedulerProvider) {
        this.mAppRepository = mAppRepository;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.sources = new MutableLiveData<>();
        loadNewsHeadlines();
    }

    private void loadSourcesFromLocalDB() {
        mCompositeDisposable.add(mAppRepository.getAllSourceFromDb()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(sourceEntityList->{
                            Log.d("SnappNews", "saveSourcesInLocalDB: " + sourceEntityList);
                        }, throwable -> {
                            Log.d("SnappNews", "Errrrooooor");
                        }
                )
        );
    }

    private void saveSourcesInLocalDB() {
        SourceEntity sourceEntity = new SourceEntity();
        sourceEntity.id = "abc-news";
        sourceEntity.name = "ABC News";
        sourceEntity.description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.";
        sourceEntity.url = "https://abcnews.go.com";
        sourceEntity.category = "general";
        sourceEntity.language = "en";
        sourceEntity.country = "us";
        List<SourceEntity> list = new ArrayList<>();
        list.add(sourceEntity);

        mCompositeDisposable.add(
                mAppRepository.saveSourceListInDb(list)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(()->{
                    Log.d("SnappNews", "saveSourcesInLocalDB: ");
                        }, throwable -> {
                    Log.d("SnappNews", "Errrrooooor");
                }
        )
        );
    }

    private void loadNewsHeadlines() {
        mCompositeDisposable.add(
                mAppRepository.getHeadlinesApiCall("abc-news")
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(new DisposableObserver<HeadLineReponse>() {
                    @Override
                    public void onNext(HeadLineReponse headLineReponse) {
                        Log.d("SnappNews", "loadNewsHeadlines: " + headLineReponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("SnappNews", "loadNewsHeadlines: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("SnappNews", "loadNewsHeadlines: ");
                    }
                })
        );

//        headlineEntities -> {
//            Log.d("SnappNews", "loadNewsHeadlines: " + headlineEntities);
//        },throwable -> {
//            Log.e("SnappNews", "loadNewsHeadlines: ", throwable);
//        }
    }

    private void loadNewsSources() {
        mCompositeDisposable.add(
                mAppRepository.getSourcesApiCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sourceEntities -> Log.d("SnappNews", "loadNewsSources: " + sourceEntities)
                        , throwable -> Log.e("SnappNews", "loadNewsSources: ", throwable))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    private MutableLiveData<List<SourceEntity>> getSources(){
        return sources;
    }
}
