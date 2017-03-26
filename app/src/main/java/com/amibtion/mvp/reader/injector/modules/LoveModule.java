package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.ViewPagerAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.manage.love.LoveActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class LoveModule {

    private final LoveActivity mView;

    public LoveModule(LoveActivity mView) {
        this.mView = mView;
    }
    @PerActivity
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getSupportFragmentManager());
    }
}
