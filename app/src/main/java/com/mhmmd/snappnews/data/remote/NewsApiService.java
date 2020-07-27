package com.mhmmd.snappnews.data.remote;

import com.mhmmd.snappnews.data.model.api.HeadLineReponse;
import com.mhmmd.snappnews.data.model.api.SourceResponse;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("sources?apiKey=3646f6f9b8554c0faba1c27089980a0f")
    Observable<SourceResponse> getSourcesApiCall();

    @GET("top-headlines?/")
    Observable<HeadLineReponse> getHeadlinesApiCall(@Query("sources") String source, @Query("apiKey") String apikey);
}
