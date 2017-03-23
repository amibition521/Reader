package com.amibtion.mvp.reader.module.manage.download.complete;

import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.local.table.VideoInfoDao;
import com.amibtion.mvp.reader.module.base.ILocalView;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.utils.ListUtils;
import com.dl7.downloaderlib.model.DownloadStatus;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/23.
 */

public class VideoCompletePresenter implements IRxBusPresenter {

    private final ILocalView mView;
    private final VideoInfoDao mDbDao;
    private final RxBus mRxBus;

    public VideoCompletePresenter(ILocalView mView, VideoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder()
                .where(VideoInfoDao.Properties.DownloadStatus.eq(DownloadStatus.COMPLETE))
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<VideoInfo>>() {
                    @Override
                    public void call(List<VideoInfo> videoInfoList) {
                        if (ListUtils.isEmpty(videoInfoList)){
                            mView.noData();
                        } else {
                            mView.loadData(videoInfoList);
                        }

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
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }
}
