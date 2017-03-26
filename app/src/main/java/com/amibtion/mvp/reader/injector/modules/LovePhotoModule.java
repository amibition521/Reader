package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.BeautyPhotoAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.manage.love.photo.LovePhotoFragment;
import com.amibtion.mvp.reader.module.manage.love.photo.LovePhotoPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class LovePhotoModule {

    private final LovePhotoFragment mView;

    public LovePhotoModule(LovePhotoFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new LovePhotoPresenter(mView,daoSession.getBeautyPhotoInfoDao(),rxBus);
    }

    @Provides
    @PerFragment
    public BaseQuickAdapter provideAdapter(){
        return new BeautyPhotoAdapter(mView);
    }
}
