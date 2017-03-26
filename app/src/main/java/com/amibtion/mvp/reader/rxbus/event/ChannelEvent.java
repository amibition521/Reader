package com.amibtion.mvp.reader.rxbus.event;

import android.support.annotation.IntDef;

import com.amibtion.mvp.reader.local.table.NewsTypeInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nieyuxin on 2017/3/13.
 * 数据库更新事件
 */

public class ChannelEvent {

    public static final int ADD_EVENT = 301;
    public static final int DEL_EVENT = 401;
    public static final int SWAP_EVENT = 501;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({ADD_EVENT,DEL_EVENT,SWAP_EVENT})
    public @interface ChannelEventType{}

    public int eventType;
    public NewsTypeInfo newsInfo;
    public int fromPos = -1;
    public int toPos = -1;

    public ChannelEvent (@ChannelEventType int eventType,NewsTypeInfo newsInfo){
        this.eventType = eventType;
        this.newsInfo = newsInfo;
    }

    public ChannelEvent (@ChannelEventType int eventType){
        this.eventType = eventType;
    }

    public ChannelEvent(@ChannelEventType int eventType,int fromPos,int toPos){
        this.eventType = eventType;
        this.fromPos = fromPos;
        this.toPos = toPos;
    }
}
