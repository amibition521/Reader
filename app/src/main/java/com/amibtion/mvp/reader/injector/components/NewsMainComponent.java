package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.NewsMainModule;
import com.amibtion.mvp.reader.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
