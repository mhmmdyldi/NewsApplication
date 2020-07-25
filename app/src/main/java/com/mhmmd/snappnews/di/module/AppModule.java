package com.mhmmd.snappnews.di.module;

import android.app.Application;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mhmmd.snappnews.data.AppRepository;
import com.mhmmd.snappnews.data.AppRepositoryImp;
import com.mhmmd.snappnews.data.local.db.AppDatabase;
import com.mhmmd.snappnews.data.local.db.DbHelper;
import com.mhmmd.snappnews.data.local.db.DbHelperImp;
import com.mhmmd.snappnews.data.remote.ApiHelper;
import com.mhmmd.snappnews.data.remote.ApiHelperImp;
import com.mhmmd.snappnews.data.remote.NewsApiService;
import com.mhmmd.snappnews.di.DatabaseInfo;
import com.mhmmd.snappnews.utils.AppConstants;
import com.mhmmd.snappnews.utils.rx.AppSchedulerProvider;
import com.mhmmd.snappnews.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImp appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(DbHelperImp appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    NewsApiService provideNewsApiService() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApiService.class);
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    AppRepository provideRepository(AppRepositoryImp appRepositoryImp) {
        return appRepositoryImp;
    }


}
