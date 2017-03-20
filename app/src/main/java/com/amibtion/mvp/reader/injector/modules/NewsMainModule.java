package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.ViewPagerAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class NewsMainModule {

    private final NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus){
        return new NewsMainPresenter(mView,daoSession.getNewsTypeInfoDao(),rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter provideViewAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
