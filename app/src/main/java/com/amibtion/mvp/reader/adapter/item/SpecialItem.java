package com.amibtion.mvp.reader.adapter.item;

import com.amibtion.mvp.reader.api.bean.NewsItemInfo;
import com.dl7.recycler.entity.SectionEntity;

/**
 * Created by nieyuxin on 2017/3/19.
 * 专题列表项
 */

public class SpecialItem extends SectionEntity<NewsItemInfo> {

    public SpecialItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SpecialItem(NewsItemInfo newsItemInfo) {
        super(newsItemInfo);
    }
}
