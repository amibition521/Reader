package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.VideoListAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.video.list.VideoListFragment;
import com.amibtion.mvp.reader.module.video.list.VideoListPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class VideoListModule {

    private final VideoListFragment mView;

    private final String mVideoId;

    public VideoListModule(VideoListFragment mView, String mVideoId) {
        this.mView = mView;
        this.mVideoId = mVideoId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new VideoListPresenter(mView,mVideoId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new VideoListAdapter(mView.getContext());
    }
}
