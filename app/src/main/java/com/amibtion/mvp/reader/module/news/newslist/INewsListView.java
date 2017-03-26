package com.amibtion.mvp.reader.module.news.newslist;

import com.amibtion.mvp.reader.adapter.item.NewsMultiItem;
import com.amibtion.mvp.reader.api.bean.NewsInfo;
import com.amibtion.mvp.reader.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {

    void loadAdData(NewsInfo newsInfo);
}
