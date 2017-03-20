package com.amibtion.mvp.reader.injector.components;

import android.content.Context;

import com.amibition.mvp.reader.Application;
import com.amibtion.mvp.reader.injector.modules.ApplicationModule;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nieyuxin on 2017/3/12.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
