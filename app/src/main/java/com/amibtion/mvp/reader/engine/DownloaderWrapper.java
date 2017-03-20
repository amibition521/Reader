package com.amibtion.mvp.reader.engine;

import android.text.TextUtils;

import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.local.table.VideoInfoDao;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.rxbus.event.VideoEvent;
import com.amibtion.mvp.reader.utils.StringUtils;
import com.dl7.downloaderlib.DownloadListener;
import com.dl7.downloaderlib.FileDownloader;
import com.dl7.downloaderlib.entity.FileInfo;
import com.dl7.downloaderlib.model.DownloadStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/3/20.
 * 下载器封装
 */

public final class DownloaderWrapper {

    private static List<VideoInfo> sDLVideoList = new ArrayList<>();
    private static RxBus sRxBus;
    private static VideoInfoDao sDbDao;

    private DownloaderWrapper() { throw new AssertionError();}

    public static void init(RxBus rxBus,VideoInfoDao videoInfoDao){
        sRxBus = rxBus;
        sDbDao = videoInfoDao;
    }

    /**
     * 开始下载
     * @param info
     */
    public static void start(VideoInfo info){
        info.setDownloadStatus(DownloadStatus.WAIT);
        // 有高清就用高清的，没有用另一个
        if (TextUtils.isEmpty(info.getMp4Hd_url())){
            info.setVideoUrl(info.getMp4_url());
        } else {
            info.setVideoUrl(info.getMp4Hd_url());
        }
        // 插入或更新
        sDbDao.insertOrReplace(info);
        sDLVideoList.add(info);
        // 通知 Video 主界面刷新下载数
        sRxBus.post(new VideoEvent());

        FileDownloader.start(info.getVideoUrl(), StringUtils.clipFileName(info.getVideoUrl()),new ListenerWrapper());
    }

    /**
     * 暂停下载
     * @param info
     */
    public static void stop(VideoInfo info){
        FileDownloader.stop(info.getVideoUrl());
    }

    /**
     * 取消下载，不会删除已下载完的文件
     * @param info
     */
    public static void cancel(VideoInfo info){
        FileDownloader.cancel(info.getVideoUrl());
        sDbDao.delete(info);
        sRxBus.post(new VideoEvent());
        sRxBus.post(new FileInfo(DownloadStatus.CANCEL,info.getVideoUrl(),StringUtils.clipFileName(info.getVideoUrl()), (int) info.getTotalSize()));
        sDLVideoList.remove(_findApp(info.getVideoUrl()));
    }

    /**
     * 删除会把下载完成的文件清除
     * @param info
     */
    public static void delete(VideoInfo info){
        // 路径要在 FileDownloader.cancel 前获取
        String path = FileDownloader.getFilePathByUrl(info.getVideoUrl());
        FileDownloader.cancel(info.getVideoUrl());

        sDbDao.delete(info);
        sRxBus.post(new VideoEvent());
        sDLVideoList.remove(_findApp(info.getVideoUrl()));
        File file = new File(path);
        if (file.exists()){
            file.delete();
        }
    }

    static class ListenerWrapper implements DownloadListener {

        @Override
        public void onStart(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
        }

        @Override
        public void onUpdate(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);

        }

        @Override
        public void onStop(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            sDLVideoList.remove(_findApp(fileInfo.getUrl()));

        }

        @Override
        public void onComplete(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            sRxBus.post(new VideoEvent());
            com.orhanobut.logger.Logger.e("onComplete"+fileInfo.toString());

        }

        @Override
        public void onCancel(FileInfo fileInfo) {
            _updateVideoInfo(fileInfo);
            sRxBus.post(fileInfo);
            sDLVideoList.remove(_findApp(fileInfo.getUrl()));
        }

        @Override
        public void onError(FileInfo fileInfo, String errorMsg) {
            _updateVideoInfo(fileInfo);
            com.orhanobut.logger.Logger.e(errorMsg);

            sRxBus.post(fileInfo);
        }
    }

    /**
     * 查找app
     * @param url
     * @return
     */
    private static VideoInfo _findApp(String url){
        for(VideoInfo appInfo:sDLVideoList){
            if (appInfo.getVideoUrl().equals(url)){
                return appInfo;
            }
        }
        return null;
    }

    private static void _updateVideoInfo(FileInfo fileInfo){
        VideoInfo info = _findApp(fileInfo.getUrl());
        if (info != null){
            if (fileInfo.getTotalBytes() != 0){
                info.setTotalSize(fileInfo.getTotalBytes());
                info.setLoadedSize(fileInfo.getLoadBytes());
                info.setDownloadSpeed(fileInfo.getSpeed());
            }

        }
        info.setDownloadStatus(fileInfo.getStatus());
        sDbDao.update(info);
    }


}
