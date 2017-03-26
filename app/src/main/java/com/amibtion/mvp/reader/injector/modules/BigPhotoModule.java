package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.PhotoPagerAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.local.table.BeautyPhotoInfo;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.photo.bigphoto.BigPhotoActivity;
import com.amibtion.mvp.reader.module.photo.bigphoto.BigPhotoPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Module
public class BigPhotoModule {

    private final BigPhotoActivity mView;
    private List<BeautyPhotoInfo> mPhotoList;

    public BigPhotoModule(BigPhotoActivity mView, List<BeautyPhotoInfo> mPhotoList) {
        this.mView = mView;
        this.mPhotoList = mPhotoList;
    }
    @PerActivity
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new BigPhotoPresenter(mView,daoSession.getBeautyPhotoInfoDao(),mPhotoList,rxBus);
    }
    @PerActivity
    @Provides
    public PhotoPagerAdapter provideAdapter(){
        return new PhotoPagerAdapter(mView);
    }


}
