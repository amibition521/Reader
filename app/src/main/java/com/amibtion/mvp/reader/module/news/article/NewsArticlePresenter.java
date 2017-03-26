package com.amibtion.mvp.reader.module.news.article;

import android.util.Log;

import com.amibtion.mvp.reader.api.RetrofitService;
import com.amibtion.mvp.reader.api.bean.NewsDetailInfo;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.utils.ListUtils;
import com.amibtion.mvp.reader.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public class NewsArticlePresenter implements IBasePresenter {

    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\"/>";

    private final String mNewsId;
    private final INewsArticleView mView;

    public NewsArticlePresenter(String mNewsId, INewsArticleView mView) {
        this.mNewsId = mNewsId;
        this.mView = mView;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getNewsDetail(mNewsId)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .doOnNext(new Action1<NewsDetailInfo>() {
                    @Override
                    public void call(NewsDetailInfo newsDetailInfo) {
                        _handleRichTextWithImg(newsDetailInfo);
                    }
                })
                .compose(mView.<NewsDetailInfo>bindToLife())
                .subscribe(new Subscriber<NewsDetailInfo>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.showNetError(new EmptyLayout.OnRetryListener() {
                            @Override
                            public void onRetry() {
                                getData(false);
                            }
                        });
                    }

                    @Override
                    public void onNext(NewsDetailInfo newsDetailInfo) {
                        mView.loadData(newsDetailInfo);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }

    private void _handleRichTextWithImg(NewsDetailInfo newsDetailBean){
        if (!ListUtils.isEmpty(newsDetailBean.getImg())){
            String body = newsDetailBean.getBody();
            for (NewsDetailInfo.ImgEntity imgEntity : newsDetailBean.getImg()){
                String ref = imgEntity.getRef();
                String src = imgEntity.getSrc();
                String img = HTML_IMG_TEMPLATE.replace("http",src);
                body = body.replaceAll(ref,img);
                Logger.w(img);
                Logger.i(body);
            }

            newsDetailBean.setBody(body);
        }
    }
}
