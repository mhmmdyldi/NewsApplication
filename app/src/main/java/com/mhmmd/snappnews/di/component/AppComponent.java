package com.mhmmd.snappnews.di.component;

import android.app.Application;

import com.mhmmd.snappnews.SnappNewsApp;
import com.mhmmd.snappnews.di.module.ActivityBuilder;
import com.mhmmd.snappnews.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
    void inject(SnappNewsApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
