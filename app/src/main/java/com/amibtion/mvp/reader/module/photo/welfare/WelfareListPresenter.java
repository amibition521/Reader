package com.amibtion.mvp.reader.module.photo.welfare;

import com.amibtion.mvp.reader.api.RetrofitService;
import com.amibtion.mvp.reader.api.bean.WelfarePhotoInfo;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.utils.ImageLoader;
import com.amibtion.mvp.reader.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public class WelfareListPresenter implements IBasePresenter {

    private WelfareListFragment mView;

    private int mPage = 1;


    public WelfareListPresenter(WelfareListFragment view) {
        this.mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getWelfarePhoto(mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<WelfarePhotoInfo>>() {
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
                    public void onNext(List<WelfarePhotoInfo> photoList) {
                        mView.loadData(photoList);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getWelfarePhoto(mPage)
                .compose(mTransformer)
                .subscribe(new Subscriber<List<WelfarePhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<WelfarePhotoInfo> photoList) {
                        mView.loadMoreData(photoList);
                        mPage++;
                    }
                });
    }

    /**
     * 统一变换
     */
    private Observable.Transformer<WelfarePhotoInfo, List<WelfarePhotoInfo>> mTransformer = new Observable.Transformer<WelfarePhotoInfo, List<WelfarePhotoInfo>>() {

        @Override
        public Observable<List<WelfarePhotoInfo>> call(Observable<WelfarePhotoInfo> welfarePhotoInfoObservable) {
            return welfarePhotoInfoObservable
                    .observeOn(Schedulers.io())
                    // 接口返回的数据是没有宽高参数的，所以这里设置图片的宽度和高度，速度会慢一点
                    .filter(new Func1<WelfarePhotoInfo, Boolean>() {
                        @Override
                        public Boolean call(WelfarePhotoInfo photoBean) {
                            try {
                                photoBean.setPixel(ImageLoader.calePhotoSize(mView.getContext(), photoBean.getUrl()));
                                return true;
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                return false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                return false;
                            }
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .toList()
                    .compose(mView.<List<WelfarePhotoInfo>>bindToLife());
        }
    };
}

