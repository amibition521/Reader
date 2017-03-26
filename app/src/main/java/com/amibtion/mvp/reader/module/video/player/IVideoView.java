package com.amibtion.mvp.reader.module.video.player;

import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.module.base.IBaseView;

import java.io.InputStream;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public interface IVideoView extends IBaseView  {

    /**
     * 获取Video数据
     * @param data 数据
     */
    void loadData(VideoInfo data);

    /**
     * 获取弹幕数据
     * @param inputStream 数据
     */
    void loadDanmakuData(InputStream inputStream);

}
