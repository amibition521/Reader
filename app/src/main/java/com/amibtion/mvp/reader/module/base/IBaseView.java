package com.amibtion.mvp.reader.module.base;

import com.amibtion.mvp.reader.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by nieyuxin on 2017/3/12.
 *
 */

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏动画
     */
    void hideLoading();

    /**
     * 显示网络错误
     * @param onRetryListener 点击监听
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T>LifecycleTransformer<T> bindToLife();

    /**
     * 完成刷新
     */
    void finishRefresh();

}
