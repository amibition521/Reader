package com.amibtion.mvp.reader.injector.components;

import android.app.Activity;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
