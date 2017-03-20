package com.amibtion.mvp.reader.local.table;

import android.os.Parcel;
import android.os.Parcelable;

import com.dl7.downloaderlib.model.DownloadStatus;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by nieyuxin on 2017/3/17.
 */
@Entity
public class VideoInfo implements Parcelable {

    /**
     * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/HD/wlncJ2098-mobile.mp4
     * cover : http://img3.cache.netease.com/3g/2015/1/12/20150112103113e10a2.png
     * title : 袁腾飞《这个历史挺靠谱》
     * replyBoard : videonews_bbs
     * playCount : 1024733
     * sectiontitle : 第15集:科举制让屌丝逆袭
     * replyid : AG4JHJUR008535RB
     * mp4_url : http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/SD/wlncJ2098-mobile.mp4
     * length : 591
     * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/HD/movie_index.m3u8
     * latest :
     * vid : VAG4JHJUR
     * ptime : 2015-01-28 15:59:31
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/SD/movie_index.m3u8
     */

    private String vid;
    private String mp4Hd_url;
    private String cover;
    private String title;

    private String sectiontitle;
    private String mp4_url;
    private int length;
    private String m3u8Hd_url;
    private String ptime;
    private String m3u8_url;

    //下载地址
    private String videoUrl;
    //文件大小 字节
    private long totalSize;
    //已加载大小
    private long loadedSize;
    //下载状态
    private int downloadStatus = DownloadStatus.NORMAL;
    //下载速度
    private long downloadSpeed;
    //是否收藏
    private boolean isCollect;

    protected VideoInfo(Parcel in) {
        vid = in.readString();
        mp4Hd_url = in.readString();
        cover = in.readString();
        title = in.readString();
        sectiontitle = in.readString();
        mp4_url = in.readString();
        length = in.readInt();
        m3u8Hd_url = in.readString();
        ptime = in.readString();
        m3u8_url = in.readString();
        videoUrl = in.readString();
        totalSize = in.readLong();
        loadedSize = in.readLong();
        downloadStatus = in.readInt();
        downloadSpeed = in.readLong();
        isCollect = in.readByte() != 0;
    }

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel in) {
            return new VideoInfo(in);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getLoadedSize() {
        return loadedSize;
    }

    public void setLoadedSize(long loadedSize) {
        this.loadedSize = loadedSize;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public long getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(long downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "vid='" + vid + '\'' +
                ", mp4Hd_url='" + mp4Hd_url + '\'' +
                ", cover='" + cover + '\'' +
                ", title='" + title + '\'' +
                ", sectiontitle='" + sectiontitle + '\'' +
                ", mp4_url='" + mp4_url + '\'' +
                ", length=" + length +
                ", m3u8Hd_url='" + m3u8Hd_url + '\'' +
                ", ptime='" + ptime + '\'' +
                ", m3u8_url='" + m3u8_url + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", totalSize=" + totalSize +
                ", loadedSize=" + loadedSize +
                ", downloadStatus=" + downloadStatus +
                ", downloadSpeed=" + downloadSpeed +
                ", isCollect=" + isCollect +
                '}';
    }

    public VideoInfo() {
    }
    @Generated(hash = 1048022349)
    public VideoInfo(String vid, String mp4Hd_url, String cover, String title, String sectiontitle, String mp4_url, int length, String m3u8Hd_url, String ptime, String m3u8_url, String videoUrl, long totalSize, long loadedSize, int downloadStatus, long downloadSpeed, boolean isCollect) {
        this.vid = vid;
        this.mp4Hd_url = mp4Hd_url;
        this.cover = cover;
        this.title = title;
        this.sectiontitle = sectiontitle;
        this.mp4_url = mp4_url;
        this.length = length;
        this.m3u8Hd_url = m3u8Hd_url;
        this.ptime = ptime;
        this.m3u8_url = m3u8_url;
        this.videoUrl = videoUrl;
        this.totalSize = totalSize;
        this.loadedSize = loadedSize;
        this.downloadStatus = downloadStatus;
        this.downloadSpeed = downloadSpeed;
        this.isCollect = isCollect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vid);
        dest.writeString(mp4Hd_url);
        dest.writeString(cover);
        dest.writeString(title);
        dest.writeString(sectiontitle);
        dest.writeString(mp4_url);
        dest.writeInt(length);
        dest.writeString(m3u8Hd_url);
        dest.writeString(ptime);
        dest.writeString(m3u8_url);
        dest.writeString(videoUrl);
        dest.writeLong(totalSize);
        dest.writeLong(loadedSize);
        dest.writeInt(downloadStatus);
        dest.writeLong(downloadSpeed);
        dest.writeByte((byte) (isCollect ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoInfo videoInfo = (VideoInfo) o;

        return vid != null ? vid.equals(videoInfo.vid) : videoInfo.vid == null;

    }

    @Override
    public int hashCode() {
        return vid != null ? vid.hashCode() : 0;
    }

    public boolean getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }
}
