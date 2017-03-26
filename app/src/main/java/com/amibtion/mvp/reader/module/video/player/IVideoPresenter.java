package com.amibtion.mvp.reader.module.video.player;

import com.amibtion.mvp.reader.local.table.DanmakuInfo;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public interface IVideoPresenter extends ILocalPresenter<VideoInfo> {
    /**
     * 添加一条弹幕到数据库
     * @param danmakuInfo
     */
    void addDanmaku(DanmakuInfo danmakuInfo);

    /**
     * 清空该视频所有缓存弹幕
     */
    void cleanDanmaku();

}
