package com.amibtion.mvp.reader.injector.components;

import android.content.Context;

/**
 * Created by nieyuxin on 2017/3/12.
 */

public interface ApplicationComponent {

    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
