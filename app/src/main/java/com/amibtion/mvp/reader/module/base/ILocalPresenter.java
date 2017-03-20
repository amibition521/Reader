package com.amibtion.mvp.reader.module.base;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 * 提供本地数据库操作的 Presenter
 */

public interface ILocalPresenter<T> extends IBasePresenter {

    void insert(T data);

    void delete(T data);

    void update(List<T> list);
}
