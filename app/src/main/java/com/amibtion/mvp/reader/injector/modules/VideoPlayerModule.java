package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class VideoPlayerModule {

    private final VideoPlayerActivity mView;

    private final VideoInfo mVideoInfo;

    public VideoPlayerModule(VideoPlayerActivity mView, VideoInfo mVideoInfo) {
        this.mView = mView;
        this.mVideoInfo = mVideoInfo;
    }

    @PerActivity
    @Provides
    public IVideoPresenter providePresenter(DaoSession daoSession, RxBus rxBus){
        return new VideoPlayerPresenter(mView,daoSession.getVideoInfoDao(),rxBus);
    }
}
