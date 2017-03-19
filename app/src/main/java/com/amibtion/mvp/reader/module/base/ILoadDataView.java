package com.amibtion.mvp.reader.module.base;

/**
 * Created by nieyuxin on 2017/3/18.
 * 加载数据的界面接口
 */

public interface ILoadDataView<T> extends IBaseView {

    void loadData(T data);

    void loadMoreData(T data);

    void loadNoData();

}
