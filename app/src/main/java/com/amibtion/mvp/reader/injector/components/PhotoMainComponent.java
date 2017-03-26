package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.PhotoMainModule;
import com.amibtion.mvp.reader.module.photo.main.PhotoMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = PhotoMainModule.class)
public interface PhotoMainComponent {
    void inject(PhotoMainFragment fragment);
}
