package com.amibtion.mvp.reader.module.news.newslist;

import com.amibtion.mvp.reader.adapter.item.NewsMultiItem;
import com.amibtion.mvp.reader.api.NewsUtils;
import com.amibtion.mvp.reader.api.RetrofitService;
import com.amibtion.mvp.reader.api.bean.NewsInfo;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.widget.EmptyLayout;

import java.util.List;
import java.util.Observable;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public class NewsListPresenter implements IBasePresenter {

    private INewsListView mView;
    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(INewsListView mView, String mNewsId) {
        this.mView = mView;
        this.mNewsId = mNewsId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.getNewsList(mNewsId,mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!isRefresh){
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Func1<NewsInfo, Boolean>() {
                    @Override
                    public Boolean call(NewsInfo newsInfo) {
                        if (NewsUtils.isAbNews(newsInfo)){
                            mView.loadAdData(newsInfo);
                        }
                        return !NewsUtils.isAbNews(newsInfo);
                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {

                    @Override
                    public void onCompleted() {
                        if (isRefresh){
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsMultiItems) {
                        mView.loadData(newsMultiItems);
                        mPage++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isRefresh){
                            mView.finishRefresh();
                        } else {
                            mView.showNetError(new EmptyLayout.OnRetryListener() {
                                @Override
                                public void onRetry() {
                                    getData(false);
                                }
                            });
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    private rx.Observable.Transformer<NewsInfo,List<NewsMultiItem>> mTransformer = new rx.Observable.Transformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public rx.Observable<List<NewsMultiItem>> call(rx.Observable<NewsInfo> newsInfoObservable) {
            return newsInfoObservable
                    .map(new Func1<NewsInfo, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem call(NewsInfo newsInfo) {

                            if (NewsUtils.isNewsPhotoSet(newsInfo.getSkipType())){
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET,newsInfo);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL,newsInfo);
                        }
                    })
                    .toList()
                    .compose(mView.<List<NewsMultiItem>>bindToLife());
        }
    };
}
