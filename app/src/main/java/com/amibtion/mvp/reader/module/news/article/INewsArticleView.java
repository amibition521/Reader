package com.amibtion.mvp.reader.module.news.article;

import com.amibtion.mvp.reader.api.bean.NewsDetailInfo;
import com.amibtion.mvp.reader.module.base.IBaseView;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public interface INewsArticleView extends IBaseView {

    /**
     * 显示数据
     * @param newsDetailInfo
     */
    void loadData(NewsDetailInfo newsDetailInfo);
}
