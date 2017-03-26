package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.ViewPagerAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.module.photo.main.PhotoMainFragment;
import com.amibtion.mvp.reader.module.photo.main.PhotoMainPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class PhotoMainModule {

    private final PhotoMainFragment mView;

    public PhotoMainModule(PhotoMainFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePhotosPresenter(DaoSession daoSession, RxBus rxBus){
        return new PhotoMainPresenter(mView,daoSession.getBeautyPhotoInfoDao(),rxBus);
    }
}
