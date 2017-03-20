package com.amibtion.mvp.reader.injector.components;

import com.amibition.mvp.reader.Application;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.VideoMainModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = VideoMainModule.class)
public interface VideoMainComplete {
    void inject(VideoMainFragment fragment);
}
