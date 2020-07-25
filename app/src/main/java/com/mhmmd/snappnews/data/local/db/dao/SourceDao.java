package com.mhmmd.snappnews.data.local.db.dao;

import com.mhmmd.snappnews.data.model.db.SourceEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(SourceEntity source);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<SourceEntity> sources);

    @Query("SELECT * FROM sources_table")
    Single<List<SourceEntity>> loadAll();

    @Delete
    void delete(SourceEntity source);

    @Query("DELETE FROM headlines_table")
    Completable deleteAll();

}
