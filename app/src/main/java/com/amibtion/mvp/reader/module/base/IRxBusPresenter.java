package com.amibtion.mvp.reader.module.base;

import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/18.
 * RxBus Presenter
 */

public interface IRxBusPresenter extends IBasePresenter {

    <T> void registerRxBus(Class<T> eventType,Action1<T> action);

    void unregisterRxBus();


}
