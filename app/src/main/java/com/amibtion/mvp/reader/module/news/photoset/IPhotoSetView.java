package com.amibtion.mvp.reader.module.news.photoset;


import com.amibtion.mvp.reader.api.bean.PhotoSetInfo;
import com.amibtion.mvp.reader.module.base.IBaseView;

/**
 * Created by long on 2016/8/29.
 * 图集界面接口
 */
public interface IPhotoSetView extends IBaseView {

    /**
     * 显示数据
     * @param photoSetBean 图集
     */
    void loadData(PhotoSetInfo photoSetBean);
}
