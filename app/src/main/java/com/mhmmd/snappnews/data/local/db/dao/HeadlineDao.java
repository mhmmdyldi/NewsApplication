package com.mhmmd.snappnews.data.local.db.dao;

import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface HeadlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(HeadlineEntity headline);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<HeadlineEntity> headline);

    @Query("SELECT * FROM headlines_table")
    Single<List<HeadlineEntity>> loadAll();

    @Query("DELETE FROM headlines_table WHERE headlines_table.id == :sourceId")
    Completable delete(String sourceId);

    @Query("DELETE FROM headlines_table")
    Completable deleteAll();

    @Query("SELECT * FROM headlines_table WHERE headlines_table.id == :sourceId")
    Single<List<HeadlineEntity>> loadArticlesOf(String sourceId);

}
