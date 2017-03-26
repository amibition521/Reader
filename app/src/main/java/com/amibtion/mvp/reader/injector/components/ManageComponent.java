package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.modules.ChannelModule;
import com.amibtion.mvp.reader.module.news.channel.ChannelActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ChannelModule.class)
public interface ManageComponent {
    void inject(ChannelActivity activity);
}
