package com.amibtion.mvp.reader.injector.components;

import com.amibition.mvp.reader.Application;
import com.amibtion.mvp.reader.injector.PerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(dependencies = Application.class,modules = ChanenlModuel.class)
public interface ManageComponent {
    void inject(ChannelActivity activity);
}
