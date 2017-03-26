package com.amibtion.mvp.reader.module.news.channel;

import com.amibtion.mvp.reader.local.table.NewsTypeInfo;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public interface IChannelView {

    void loadData(List<NewsTypeInfo> checkList,List<NewsTypeInfo> uncheckList);
}
