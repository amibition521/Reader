package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.SpecialAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.news.special.SpecialActivity;
import com.amibtion.mvp.reader.module.news.special.SpecialPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class SpecialModule {

    private final SpecialActivity mView;
    private final String mSpecial;

    public SpecialModule(SpecialActivity mView, String mSpecial) {
        this.mView = mView;
        this.mSpecial = mSpecial;
    }

    @PerActivity
    @Provides
    public IBasePresenter provideSpecialPresent() {
        return new SpecialPresenter(mView,mSpecial);
    }

    @PerActivity
    @Provides
    public BaseQuickAdapter provideSpecialAdapter() {
        return new SpecialAdapter(mView);
    }
}
