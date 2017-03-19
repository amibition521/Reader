package com.amibtion.mvp.reader.adapter.item;

import android.support.annotation.IntDef;

import com.amibtion.mvp.reader.api.bean.NewsInfo;
import com.dl7.recycler.entity.MultiItemEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.amibtion.mvp.reader.adapter.item.NewsMultiItem.ITEM_TYPE_NORMAL;

/**
 * Created by nieyuxin on 2017/3/18.
 * 新闻复用列表项
 */

public class NewsMultiItem extends MultiItemEntity {

    public static final int ITEM_TYPE_NORMAL = 1;
    public static final int ITEM_TYPE_PHOTO_SET = 2;

    private NewsInfo mNewsBean;

    public NewsMultiItem(int itemType) {
        super(itemType);
    }
    public NewsMultiItem(@NewsItemType int itemType,NewsInfo newsBean){
        super(itemType);
        mNewsBean = newsBean;
    }

    public NewsInfo getmNewsBean() {
        return mNewsBean;
    }

    public void setmNewsBean(NewsInfo newsBean){
        mNewsBean = newsBean;
    }

    @Override
    public void setItemType(int itemType) {
        super.setItemType(itemType);

    }
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_NORMAL,ITEM_TYPE_PHOTO_SET})
    public @interface NewsItemType {}
}
