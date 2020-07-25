package com.mhmmd.snappnews.data.model.db;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sources_table")
public class SourceEntity {

    @PrimaryKey
    @NonNull
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
