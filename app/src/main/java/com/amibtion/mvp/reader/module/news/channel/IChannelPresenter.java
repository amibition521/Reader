package com.amibtion.mvp.reader.module.news.channel;

import com.amibtion.mvp.reader.module.base.ILocalPresenter;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public interface IChannelPresenter<T> extends ILocalPresenter<T> {

    /**
     * 交换
     * @param fromPos
     * @param toPos
     */
    void swap(int fromPos,int toPos);
}
