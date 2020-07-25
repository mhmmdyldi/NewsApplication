package com.mhmmd.snappnews.data.local.db;

import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class DbHelperImp implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public DbHelperImp(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }

    @Override
    public Completable saveSourceListInDb(List<SourceEntity> sourceList) {
        return mAppDatabase.sourceDao().insertAll(sourceList);
    }

    @Override
    public Single<List<SourceEntity>> getAllSourceFromDb() {
        return mAppDatabase.sourceDao().loadAll();
    }

    @Override
    public Completable InsertSourceInDb(SourceEntity source) {
        return mAppDatabase.sourceDao().insert(source);
    }

    @Override
    public Completable saveHeadlineListInDb(List<HeadlineEntity> headlineList) {
        return mAppDatabase.headlineDao().insertAll(headlineList);
    }

    @Override
    public Completable InsertHeadlineInDb(HeadlineEntity headline) {
        return mAppDatabase.headlineDao().insert(headline);
    }
}
