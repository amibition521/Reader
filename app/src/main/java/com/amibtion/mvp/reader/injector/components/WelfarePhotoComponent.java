package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.WelfarePhotoModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = WelfarePhotoModule.class)
public interface WelfarePhotoComponent {
    void inject(WelfarePhotoFragment fragment);
}
