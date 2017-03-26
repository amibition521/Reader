package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.adapter.NewsMultiListAdapter;
import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.injector.PerFragment;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.news.newslist.NewsListFragment;
import com.amibtion.mvp.reader.module.news.newslist.NewsListPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import org.greenrobot.greendao.annotation.Entity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@Module
public class NewsListModule {

    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment mNewsListView, String mNewsId) {
        this.mNewsListView = mNewsListView;
        this.mNewsId = mNewsId;
    }

    @PerFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView,mNewsId);
    }

    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
