package com.amibtion.mvp.reader.rxbus.event;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nieyuxin on 2017/3/13.
 */

public class VideoEvent {

    public static final int CHECK_INVALID = 400;
    public static final int CHECK_NONE = 401;
    public static final int CHECK_SOME = 402;
    public static final int CHECK_ALL = 403;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({CHECK_INVALID,CHECK_ALL,CHECK_NONE,CHECK_SOME})
    public @interface CheckStatus {}

    public int checkStatus = CHECK_INVALID;

    public VideoEvent(){}

    public VideoEvent(@CheckStatus int checkStatus){
        this.checkStatus = checkStatus;
    }
}
