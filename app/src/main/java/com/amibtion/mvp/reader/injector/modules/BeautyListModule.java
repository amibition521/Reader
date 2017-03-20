package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.BeautyPhotoAdapter;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class BeautyListModule {

    private final BeautyListFragment mView;

    public BeautyListModule(BeautyListFragment mView) {
        this.mView = mView;
    }
    @Provides
    @PerFragment
    public IBasePresenter providePresenten(){
        return new BeautyListPresenter(mView);
    }
    @PerFragment
    @Provides
    public BaseQuickAdapter providerAdapter() {
        return new BeautyPhotoAdapter(mView.getContext());
    }

}
