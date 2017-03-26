package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.LoveVideoModule;
import com.amibtion.mvp.reader.module.manage.love.video.LoveVideoFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = LoveVideoModule.class)
public interface LoveVideoComponent {
    void inject(LoveVideoFragment fragment);

}
