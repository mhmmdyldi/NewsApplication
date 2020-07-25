package com.mhmmd.snappnews.data.remote;

import com.mhmmd.snappnews.data.model.api.HeadLineReponse;
import com.mhmmd.snappnews.data.model.api.SourceResponse;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import java.util.List;
import io.reactivex.Observable;


public interface ApiHelper {

    Observable<SourceResponse> getSourcesApiCall();

    Observable<HeadLineReponse> getHeadlinesApiCall(String newsSource);
}
