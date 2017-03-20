package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = VideoListModule.class)
public interface VideoListComponent {
    void inject(VideoListFragment fragment);
}
