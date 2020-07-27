package com.mhmmd.snappnews.data.local.db;

import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DbHelper {

    Completable saveSourceListInDb(List<SourceEntity> sourceList);

    Single<List<SourceEntity>> getAllSourcesFromDb();

    Completable insertSourceInDb(SourceEntity source);

    Completable saveHeadlineListInDb(List<HeadlineEntity> headlineList);

    Completable insertHeadlineInDb(HeadlineEntity headline);

    Single<List<HeadlineEntity>> getArticlesOfSourceFromDb(String sourceId);

    Completable deleteArticlesOfSourceFromDB(String sourceId);
}
