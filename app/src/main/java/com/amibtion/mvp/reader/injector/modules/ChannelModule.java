package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.ManageAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.news.channel.ChannelActivity;
import com.amibtion.mvp.reader.module.news.channel.ChannelPresenter;
import com.amibtion.mvp.reader.module.news.channel.IChannelPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class ChannelModule {
    private final ChannelActivity mView;

    public ChannelModule(ChannelActivity mView) {
        this.mView = mView;
    }
    @Provides
    public BaseQuickAdapter provideManageAdapter() {
        return new ManageAdapter(mView);
    }
    @PerActivity
    @Provides
    public IChannelPresenter provideManagePresenter(DaoSession daoSession, RxBus rxBus){
        return new ChannelPresenter(mView,daoSession.getNewsTypeInfoDao(),rxBus);
    }

}
