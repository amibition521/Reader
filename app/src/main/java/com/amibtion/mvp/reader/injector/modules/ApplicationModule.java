package com.amibtion.mvp.reader.injector.modules;

import android.content.Context;

import com.amibtion.mvp.reader.AndroidApplication;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;
    private final DaoSession mDaoSession;
    private final RxBus mRxBus;

    public ApplicationModule(AndroidApplication mApplication, DaoSession mDaoSession, RxBus mRxBus) {
        this.mApplication = mApplication;
        this.mDaoSession = mDaoSession;
        this.mRxBus = mRxBus;
    }
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplication();
    }
    @Provides
    @Singleton
    RxBus provideRxBus(){
        return mRxBus;
    }
    @Provides
    @Singleton
    DaoSession provideDaoSession(){
        return mDaoSession;
    }

}
