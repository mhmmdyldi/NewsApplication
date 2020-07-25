package com.mhmmd.snappnews.data.model.api;

import com.google.gson.annotations.SerializedName;

public class SourceItem {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("category")
    public String category;
    @SerializedName("language")
    public String language;
    @SerializedName("country")
    public String country;
}
