package com.amibtion.mvp.reader.local.table;

import com.dl7.player.danmaku.BaseDanmakuData;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by nieyuxin on 2017/3/17.
 */

public class DanmakuInfo  extends BaseDanmakuData {

    private int type;
    private String content;
    private long time;
    private float textSize;
    private int textColor;

    private String userName;
    private String vid;

    @Generated(hash = 297831740)
    public DanmakuInfo(int type, String content, long time, float textSize, int textColor, String userName, String vid) {
        this.type = type;
        this.content = content;
        this.time = time;
        this.textSize = textSize;
        this.textColor = textColor;
        this.userName = userName;
        this.vid = vid;
    }
    @Generated(hash = 2104047588)
    public DanmakuInfo() {

    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public float getTextSize() {
        return textSize;
    }

    @Override
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public int getTextColor() {
        return textColor;
    }

    @Override
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    @Override
    public String toString() {
        return "DanmakuInfo{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", textSize=" + textSize +
                ", textColor=" + textColor +
                ", userName='" + userName + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }
}
