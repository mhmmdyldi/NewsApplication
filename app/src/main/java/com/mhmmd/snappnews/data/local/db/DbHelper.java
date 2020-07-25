package com.mhmmd.snappnews.data.local.db;

import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DbHelper {

    Completable saveSourceListInDb(List<SourceEntity> sourceList);

    Single<List<SourceEntity>> getAllSourceFromDb();

    Completable InsertSourceInDb(SourceEntity source);

    Completable saveHeadlineListInDb(List<HeadlineEntity> headlineList);

    Completable InsertHeadlineInDb(HeadlineEntity headline);
}
