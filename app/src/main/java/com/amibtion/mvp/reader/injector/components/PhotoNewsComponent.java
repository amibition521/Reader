package com.amibtion.mvp.reader.injector.components;

import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.injector.modules.PhotoNewsModule;

import dagger.Component;

/**
 * Created by nieyuxin on 2017/3/20.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class,module = PhotoNewsModule.class)
public interface PhotoNewsComponent {
    void inject(PhotoNewsFragment fragment);
}
