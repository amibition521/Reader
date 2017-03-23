package com.amibtion.mvp.reader.module.news.main;

import com.amibtion.mvp.reader.local.table.NewsTypeInfo;
import com.amibtion.mvp.reader.local.table.NewsTypeInfoDao;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public class NewsMainPresenter implements IRxBusPresenter {

    private final INewsMainView mView;
    private final NewsTypeInfoDao mDbDao;
    private final RxBus mRxBus;

    public NewsMainPresenter(INewsMainView mView, NewsTypeInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder()
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsTypeInfo>>() {
                    @Override
                    public void call(List<NewsTypeInfo> newsTypeInfos) {
                        mView.loadData(newsTypeInfos);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscibe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(throwable.toString());
            }
        });
        mRxBus.addSubsciption(this,subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }
}
