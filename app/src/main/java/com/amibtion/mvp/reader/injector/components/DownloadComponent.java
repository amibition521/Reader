package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.modules.DownloadModule;
import com.amibtion.mvp.reader.module.manage.download.DownloadActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = DownloadModule.class)
public interface DownloadComponent {
    void inject(DownloadActivity activity);
}
