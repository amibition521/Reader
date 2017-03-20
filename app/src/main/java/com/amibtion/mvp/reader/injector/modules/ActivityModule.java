package com.amibtion.mvp.reader.injector.modules;

import android.app.Activity;

import com.amibtion.mvp.reader.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/12.
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity() {
        return mActivity;
    }
}
