package com.amibtion.mvp.reader.module.base;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 * 和本地数据关联的界面接口
 */

public interface ILocalView<T> {

    void loadData(List<T> dataList);

    void noData();
}
