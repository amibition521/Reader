package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.modules.SpecialModule;
import com.amibtion.mvp.reader.module.news.special.SpecialActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(modules = SpecialModule.class)
public interface SpecialComponent {
    void inject(SpecialActivity activity);
}
