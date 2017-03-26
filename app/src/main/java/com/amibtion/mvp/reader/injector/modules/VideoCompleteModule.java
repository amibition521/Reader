package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.BaseVideoDLAdapter;
import com.amibtion.mvp.reader.adapter.VideoCompleteAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.module.manage.download.complete.VideoCompleteFragment;
import com.amibtion.mvp.reader.module.manage.download.complete.VideoCompletePresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class VideoCompleteModule {

    private final VideoCompleteFragment mView;

    public VideoCompleteModule(VideoCompleteFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new VideoCompletePresenter(mView,daoSession.getVideoInfoDao(),rxBus);
    }

    @PerFragment
    @Provides
    public BaseVideoDLAdapter provideAdapter(RxBus rxBus){
        return new VideoCompleteAdapter(mView.getContext(),rxBus);
    }
}
