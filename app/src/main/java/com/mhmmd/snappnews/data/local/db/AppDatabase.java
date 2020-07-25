package com.mhmmd.snappnews.data.local.db;

import com.mhmmd.snappnews.data.local.db.dao.HeadlineDao;
import com.mhmmd.snappnews.data.local.db.dao.SourceDao;
import com.mhmmd.snappnews.data.local.db.dao.SourceWithHeadlinesDao;
import com.mhmmd.snappnews.data.model.db.HeadlineEntity;
import com.mhmmd.snappnews.data.model.db.SourceEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SourceEntity.class, HeadlineEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SourceDao sourceDao();
    public abstract HeadlineDao headlineDao();
    public abstract SourceWithHeadlinesDao sourceWithHeadlinesDao();
}
