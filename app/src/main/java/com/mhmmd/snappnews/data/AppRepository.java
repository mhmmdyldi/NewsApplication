package com.mhmmd.snappnews.data;

import com.mhmmd.snappnews.data.local.db.DbHelper;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;
import com.mhmmd.snappnews.data.remote.ApiHelper;

import java.util.List;

import io.reactivex.Observable;

public interface AppRepository extends DbHelper, ApiHelper {

    Observable<List<SourceEntity>> getSourcesListApiCall();

    Observable<List<HeadlineEntity>> getArticlesListApiCall(String newsSource);
}
