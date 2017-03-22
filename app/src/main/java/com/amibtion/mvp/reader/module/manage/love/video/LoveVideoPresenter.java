package com.amibtion.mvp.reader.module.manage.love.video;

import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.local.table.VideoInfoDao;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.base.ILocalView;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.rxbus.event.VideoEvent;
import com.amibtion.mvp.reader.utils.ListUtils;
import com.dl7.downloaderlib.model.DownloadStatus;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/22.
 */

public class LoveVideoPresenter implements ILocalPresenter<VideoInfo> {

    private final ILocalView mView;
    private final VideoInfoDao mDbDao;
    private final RxBus mRxBus;

    public LoveVideoPresenter(ILocalView mView, VideoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder().where(VideoInfoDao.Properties.IsCollect.eq(true))
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<VideoInfo>>() {
                    @Override
                    public void call(List<VideoInfo> videoList) {
                        if(ListUtils.isEmpty(videoList)){
                            mView.noData();
                        } else {
                            mView.loadData(videoList);
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void insert(VideoInfo data) {

    }

    @Override
    public void delete(VideoInfo data) {
        data.setCollect(false);
        if (!data.isCollect() && data.getDownloadStatus() == DownloadStatus.NORMAL) {
            mDbDao.delete(data);
        } else {
            mDbDao.update(data);
        }
        if(mDbDao.queryBuilder()
                .where(VideoInfoDao.Properties.IsCollect.eq(true)).count() == 0){
            mView.noData();
        }

        mRxBus.post(new VideoEvent());
    }

    @Override
    public void update(List<VideoInfo> list) {

    }
}
