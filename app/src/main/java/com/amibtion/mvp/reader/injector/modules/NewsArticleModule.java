package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.base.IBasePresenter;

import org.greenrobot.greendao.annotation.Entity;

import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Entity
public class NewsArticleModule {

    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule(String mNewsId, NewsArticleActivity mView) {
        this.mNewsId = mNewsId;
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsArticlePresenter(mNewsId,mView);
    }
}

