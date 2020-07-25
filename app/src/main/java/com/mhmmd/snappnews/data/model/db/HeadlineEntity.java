package com.mhmmd.snappnews.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "headlines_table")
public class HeadlineEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "source_id")
    public String sourceId;
    @ColumnInfo(name = "source_name")
    public String name;
    public String author;
    public String title;
    public String description;
    public String url;
    @ColumnInfo(name = "url_to_image")
    public String urlToImage;
    @ColumnInfo(name = "published_at")
    public String publishedAt;
    public String content;
}