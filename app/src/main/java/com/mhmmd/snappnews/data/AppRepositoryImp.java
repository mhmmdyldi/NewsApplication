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
    public Single<List<SourceEntity>> getAllSourceFromDb() {
        return mDbHelper.getAllSourceFromDb();
    }

    @Override
    public Completable InsertSourceInDb(SourceEntity source) {
        return mDbHelper.InsertSourceInDb(source);
    }

    @Override
    public Completable saveHeadlineListInDb(List<HeadlineEntity> headlineList) {
        return mDbHelper.saveHeadlineListInDb(headlineList);
    }

    @Override
    public Completable InsertHeadlineInDb(HeadlineEntity headline) {
        return mDbHelper.InsertHeadlineInDb(headline);
    }

    @Override
    public Observable<SourceResponse> getSourcesApiCall() {
        return mApiHelper.getSourcesApiCall();
    }

    @Override
    public Observable<HeadLineReponse> getHeadlinesApiCall(String newsSource) {
        return mApiHelper.getHeadlinesApiCall(newsSource);
    }
}
