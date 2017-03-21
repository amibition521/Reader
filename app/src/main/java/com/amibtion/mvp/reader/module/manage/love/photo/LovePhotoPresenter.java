package com.amibtion.mvp.reader.module.manage.love.photo;

import com.amibtion.mvp.reader.local.table.BeautyPhotoInfo;
import com.amibtion.mvp.reader.local.table.BeautyPhotoInfoDao;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.base.ILocalView;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.rxbus.event.LoveEvent;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.R.id.list;

/**
 * Created by nieyuxin on 2017/3/21.
 */

public class LovePhotoPresenter implements ILocalPresenter<BeautyPhotoInfo> {

    private final ILocalView mView;
    private final BeautyPhotoInfoDao mDbDao;
    private final RxBus mRxBus;

    public LovePhotoPresenter(ILocalView mView, BeautyPhotoInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder().where(BeautyPhotoInfoDao.Properties.IsLove.eq(true))
                .rx()
                .list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<BeautyPhotoInfo>>() {
                    @Override
                    public void call(List<BeautyPhotoInfo> list) {
                        if(list.size() == 0){
                            mView.noData();
                        } else {
                            mView.loadData(list);
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void insert(BeautyPhotoInfo data) {

    }

    @Override
    public void delete(BeautyPhotoInfo data) {
        data.setLove(false);
        if (!data.isLove() && !data.isDownload() && !data.isPraise()){
            mDbDao.delete(data);
        } else {
            mDbDao.update(data);
        }

        if (mDbDao.queryBuilder().where(BeautyPhotoInfoDao.Properties.IsLove.eq(true)).count() == 0){
            mView.noData();
        }

        mRxBus.post(new LoveEvent());
    }

    @Override
    public void update(List<BeautyPhotoInfo> list) {

    }
}
