package com.amibtion.mvp.reader.module.base;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 */

public interface ILocalRxBusPresenter<E> extends IRxBusPresenter {

    void insert(E data);

    void delete(E data);

    void update(List<E> list);
}
