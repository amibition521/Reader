package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.VideoCacheModule;
import com.amibtion.mvp.reader.module.manage.download.cache.VideoCacheFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = VideoCacheModule.class)
public interface VideoCacheComponent {
    void inject(VideoCacheFragment fragment);

}
