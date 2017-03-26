package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.VideoLoveAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.manage.love.video.LoveVideoFragment;
import com.amibtion.mvp.reader.module.manage.love.video.LoveVideoPresenter;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class LoveVideoModule {

    private final LoveVideoFragment mView;

    public LoveVideoModule(LoveVideoFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public ILocalPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new LoveVideoPresenter(mView,daoSession.getVideoInfoDao(),rxBus);
    }
    @PerFragment
    @Provides
    public BaseQuickAdapter provieAdapter(){
        return new VideoLoveAdapter(mView.getContext());
    }

}
