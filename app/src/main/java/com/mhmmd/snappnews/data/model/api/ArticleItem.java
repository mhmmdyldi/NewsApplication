package com.mhmmd.snappnews.data.model.api;

import com.google.gson.annotations.SerializedName;

public class ArticleItem {
    @SerializedName("source")
    public Source source;
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToImage;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("content")
    public String content;

    public class Source {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
    }
}
