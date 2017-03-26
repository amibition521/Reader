package com.amibtion.mvp.reader.module.video.main;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public interface IVideoMainView {

    /**
     * 更新数据
     * @param lovedCount 收藏数
     */
    void updateLovedCount(int lovedCount);

    /**
     * 更新数据
     * @param downloadCount 下载中个数
     */
    void updateDownloadCount(int downloadCount);
}
