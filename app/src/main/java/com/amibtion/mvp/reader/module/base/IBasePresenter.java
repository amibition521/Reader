package com.amibtion.mvp.reader.module.base;

/**
 * Created by nieyuxin on 2017/3/12.
 */

public interface IBasePresenter {

    /**
     * 获取数据
     * @param isRefresh 判断是否是下拉刷新
     */
    void getData(boolean isRefresh);

    void getMoreData();
}
