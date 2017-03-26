package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.news.article.NewsArticleActivity;
import com.amibtion.mvp.reader.module.news.article.NewsArticlePresenter;

import org.greenrobot.greendao.annotation.Entity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class NewsArticleModule {

    private final String mNewsId;
    private final NewsArticleActivity mView;

    public NewsArticleModule( NewsArticleActivity mView,String mNewsId) {
        this.mNewsId = mNewsId;
        this.mView = mView;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsArticlePresenter(mNewsId,mView);
    }
}

