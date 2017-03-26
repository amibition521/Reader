package com.amibtion.mvp.reader.module.photo.main;

import com.amibtion.mvp.reader.local.table.BeautyPhotoInfoDao;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public class PhotoMainPresenter implements IRxBusPresenter {

    private final IPhotoMainView mView;
    private final BeautyPhotoInfoDao mDbDao;
    private final RxBus mRxBus;

    public PhotoMainPresenter(IPhotoMainView mView, BeautyPhotoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mView.updateCount((int) mDbDao.queryBuilder()
        .where(BeautyPhotoInfoDao.Properties.IsLove.eq(true)).count());

    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(throwable.toString());
            }
        });
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {

    }
}
