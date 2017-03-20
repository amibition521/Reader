package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = BigPhotoModule.class)
public interface BigPhotoComponent {
    void inject(BigPhotoActivity activity);
}
