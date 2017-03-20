package com.amibtion.mvp.reader.injector.components;

import android.content.Context;

import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.rxbus.RxBus;

/**
 * Created by nieyuxin on 2017/3/12.
 */

public interface ApplicationComponent {

    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
