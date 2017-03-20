package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.local.table.VideoInfo;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class modules = VideoCompleteModule.class)
public interface VideoCompleteComponent {
    void inject(VideoCacheFragment fragment);
}
