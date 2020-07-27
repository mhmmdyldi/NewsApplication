package com.mhmmd.snappnews.ui.newsSource;

import com.mhmmd.snappnews.data.AppRepository;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.utils.Resource;
import com.mhmmd.snappnews.utils.Status;
import com.mhmmd.snappnews.utils.rx.SchedulerProvider;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsSourceViewModel extends ViewModel {
    private final AppRepository mAppRepository;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<Resource<List<SourceEntity>>> sources;

    public NewsSourceViewModel(AppRepository mAppRepository, SchedulerProvider mSchedulerProvider) {
        this.mAppRepository = mAppRepository;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.sources = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    public MutableLiveData<Resource<List<SourceEntity>>> getSources() {
        return sources;
    }

    public void loadNewsSources() {
        loadSourcesFromLocalDB();
    }

    private void loadNewsSourcesListFromApi() {
        mCompositeDisposable.add(
                mAppRepository.getSourcesListApiCall()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(sourcesList -> {
                            saveSourcesInLocalDB(sourcesList);
                        }, throwable -> {

                        })
        );
    }

    private void saveSourcesInLocalDB(List<SourceEntity> sourcesList) {
        mCompositeDisposable.add(
                mAppRepository.saveSourceListInDb(sourcesList)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(() -> {
                                    loadSourcesFromLocalDB();
                                }, throwable -> {

                                }
                        )
        );
    }

    private void loadSourcesFromLocalDB() {
        mCompositeDisposable.add(mAppRepository.getAllSourcesFromDb()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(sourceEntityList -> {
                            if (sourceEntityList.size() > 0) {
                                sources.postValue(new Resource<>(Status.SUCCESS, sourceEntityList, null));
                            } else {
                                loadNewsSourcesListFromApi();
                            }
                        }, throwable -> {

                        }
                )
        );
    }

}
