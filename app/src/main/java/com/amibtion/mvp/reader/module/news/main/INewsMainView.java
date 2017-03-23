package com.amibtion.mvp.reader.module.news.main;

import com.amibtion.mvp.reader.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public interface INewsMainView {

    void loadData(List<NewsTypeInfo> checkList);
}
