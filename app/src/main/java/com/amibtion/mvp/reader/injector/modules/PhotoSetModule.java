package com.amibtion.mvp.reader.injector.modules;

import com.amibtion.mvp.reader.injector.PerActivity;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.module.news.photoset.PhotoSetActivity;
import com.amibtion.mvp.reader.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nieyuxin on 2017/3/20.
 */
@Module
public class PhotoSetModule {

    private final PhotoSetActivity mView;
    private final String mPhotoSetId;

    public PhotoSetModule(PhotoSetActivity mView, String mPhotoSetId) {
        this.mView = mView;
        this.mPhotoSetId = mPhotoSetId;
    }

    @PerActivity
    @Provides
    public IBasePresenter providePhotoSetPresenter() {
        return new PhotoSetPresenter(mView,mPhotoSetId);
    }
}
