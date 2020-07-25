package com.mhmmd.snappnews.data.local.db.dao;

import com.mhmmd.snappnews.data.model.db.SourceWithHeadlines;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Single;

@Dao
public interface SourceWithHeadlinesDao {
    @Transaction
    @Query("SELECT * FROM sources_table")
    Single<List<SourceWithHeadlines>> getSourceWithHeadlines();
}
