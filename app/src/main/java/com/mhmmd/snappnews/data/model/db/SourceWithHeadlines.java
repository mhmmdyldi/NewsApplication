package com.mhmmd.snappnews.data.model.db;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class SourceWithHeadlines {
    @Embedded
    public SourceEntity source;
    @Relation(parentColumn = "id", entityColumn = "source_id", entity = HeadlineEntity.class)
    public List<HeadlineEntity> departments;
}
