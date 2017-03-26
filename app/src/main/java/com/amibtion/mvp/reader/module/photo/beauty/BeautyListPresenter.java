package com.amibtion.mvp.reader.module.photo.beauty;

import com.amibtion.mvp.reader.api.RetrofitService;
import com.amibtion.mvp.reader.local.table.BeautyPhotoInfo;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.base.ILoadDataView;
import com.amibtion.mvp.reader.widget.EmptyLayout;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public class BeautyListFragment implements IBasePresenter {

    private final ILoadDataView mView;
    private int mPage = 0;

    public BeautyListFragment(ILoadDataView mView) {
        this.mView = mView;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getBeautyPhoto(mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError(new EmptyLayout.OnRetryListener() {
                            @Override
                            public void onRetry() {
                                getData(false);
                            }
                        });
                    }

                    @Override
                    public void onNext(List<BeautyPhotoInfo> beautyPhotoInfos) {
                        mView.loadData(beautyPhotoInfos);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getBeautyPhoto(mPage)
                .compose(mView.<List<BeautyPhotoInfo>>bindToLife())
                .subscribe(new Subscriber<List<BeautyPhotoInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<BeautyPhotoInfo> beautyPhotoInfos) {
                        mView.loadMoreData(beautyPhotoInfos);
                        mPage++;
                    }
                });
    }
}
