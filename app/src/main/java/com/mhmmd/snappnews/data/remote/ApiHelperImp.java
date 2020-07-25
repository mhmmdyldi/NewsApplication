package com.mhmmd.snappnews.data.remote;

import com.mhmmd.snappnews.data.model.api.HeadLineReponse;
import com.mhmmd.snappnews.data.model.api.SourceResponse;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class ApiHelperImp implements ApiHelper {

    private final NewsApiService newsApiService;

    @Inject
    public ApiHelperImp(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @Override
    public Observable<SourceResponse> getSourcesApiCall() {
        return newsApiService.getSourcesApiCall();

    }

    @Override
    public Observable<HeadLineReponse> getHeadlinesApiCall(String newsSource) {
        return newsApiService.getHeadlinesApiCall(newsSource, "3646f6f9b8554c0faba1c27089980a0f");
    }
}
