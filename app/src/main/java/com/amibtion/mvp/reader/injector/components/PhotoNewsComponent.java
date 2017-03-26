package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.PhotoNewsModule;
import com.amibtion.mvp.reader.module.photo.news.PhotoNewsFragment;

import dagger.Component;

/**
 * Created by nieyuxin on 2017/3/20.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = PhotoNewsModule.class)
public interface PhotoNewsComponent {
    void inject(PhotoNewsFragment fragment);
}
