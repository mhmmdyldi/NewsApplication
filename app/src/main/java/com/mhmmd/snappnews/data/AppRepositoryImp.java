package com.mhmmd.snappnews.data;

import com.mhmmd.snappnews.data.local.db.DbHelper;
import com.mhmmd.snappnews.data.model.api.HeadLineReponse;
import com.mhmmd.snappnews.data.model.api.SourceResponse;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppRepositoryImp implements AppRepository{
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppRepositoryImp(DbHelper mDbHelper, ApiHelper mApiHelper) {
        this.mDbHelper = mDbHelper;
        this.mApiHelper = mApiHelper;
    }

    @Override
    public Completable saveSourceListInDb(List<SourceEntity> sourceList) {
        return mDbHelper.saveSourceListInDb(sourceList);
    }

    @Override
    public Single<List<SourceEntity>> getAllSourcesFromDb() {
        return mDbHelper.getAllSourcesFromDb();
    }

    @Override
    public Completable insertSourceInDb(SourceEntity source) {
        return mDbHelper.insertSourceInDb(source);
    }

    @Override
    public Completable saveHeadlineListInDb(List<HeadlineEntity> headlineList) {
        return mDbHelper.saveHeadlineListInDb(headlineList);
    }

    @Override
    public Completable insertHeadlineInDb(HeadlineEntity headline) {
        return mDbHelper.insertHeadlineInDb(headline);
    }

    @Override
    public Single<List<HeadlineEntity>> getArticlesOfSourceFromDb(String sourceId) {
        return mDbHelper.getArticlesOfSourceFromDb(sourceId);
    }

    @Override
    public Completable deleteArticlesOfSourceFromDB(String sourceId) {
        return mDbHelper.deleteArticlesOfSourceFromDB(sourceId);
    }

    @Override
    public Observable<SourceResponse> getSourcesApiCall() {
        return mApiHelper.getSourcesApiCall();
    }

    @Override
    public Observable<HeadLineReponse> getHeadlinesApiCall(String newsSource) {
        return mApiHelper.getHeadlinesApiCall(newsSource);
    }

    @Override
    public Observable<List<SourceEntity>> getSourcesListApiCall() {
        return mApiHelper.getSourcesApiCall()
                .map(sourceResponse -> sourceResponse.sources);
    }

    @Override
    public Observable<List<HeadlineEntity>> getArticlesListApiCall(String newsSource) {
        return mApiHelper.getHeadlinesApiCall(newsSource)
                .map(headLineReponse -> headLineReponse.articles);
    }
}
