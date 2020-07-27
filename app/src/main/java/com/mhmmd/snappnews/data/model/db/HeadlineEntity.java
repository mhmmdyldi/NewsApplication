package com.mhmmd.snappnews.data.model.db;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "headlines_table")
public class HeadlineEntity {

    @PrimaryKey(autoGenerate = true)
    public long itemId;
    @SerializedName("source")
    @Embedded
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
    @ColumnInfo(name = "url_to_image")
    public String urlToImage;
    @SerializedName("publishedAt")
    @ColumnInfo(name = "published_at")
    public String publishedAt;
    @SerializedName("content")
    public String content;
}