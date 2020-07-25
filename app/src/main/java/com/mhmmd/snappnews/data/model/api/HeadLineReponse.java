package com.mhmmd.snappnews.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeadLineReponse {
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;
    @SerializedName("articles")
    public List<ArticleItem> articles;
}
