package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.WelfarePhotoAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class WelfarePhotoModule {

    private final WelfareListFragment mView;

    public WelfarePhotoModule(WelfareListFragment mView) {
        this.mView = mView;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new WelfarePhotoPresenter(mView);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new WelfarePhotoAdapter(mView.getContext());
    }
}
