package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.ViewPagerAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class DownloadModule {
    private final DownloadActivity mView;

    public DownloadModule(DownloadActivity mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter(){
        return new ViewPagerAdapter(mView.getSupportFragmentManager());
    }
    @PerActivity
    @Provides
    public IRxBusPresenter provideVideoPresenter(RxBus rxBus){
        return new DownloadPresenter(rxBus);
    }
}
