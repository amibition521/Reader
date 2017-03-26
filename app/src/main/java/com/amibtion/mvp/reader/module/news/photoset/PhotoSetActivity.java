package com.amibtion.mvp.reader.module.news.photoset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.adapter.PhotoSetAdapter;
import com.amibtion.mvp.reader.api.bean.PhotoSetInfo;
import com.amibtion.mvp.reader.injector.components.DaggerPhotoSetComponent;
import com.amibtion.mvp.reader.injector.modules.PhotoSetModule;
import com.amibtion.mvp.reader.module.base.BaseActivity;
import com.amibtion.mvp.reader.module.base.IBasePresenter;
import com.amibtion.mvp.reader.widget.PhotoViewPager;
import com.dl7.drag.DragSlopLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 图片集
 */

public class PhotoSetActivity extends BaseActivity<IBasePresenter> implements IPhotoSetView {

    private static final String PHOTO_SET_KEY = "PhotoSetKey";

    @BindView(R.id.vp_photo)
    PhotoViewPager mVpPhoto;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_index)
    TextView mTvIndex;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drag_layout)
    DragSlopLayout mDragLayout;

    private String mPhotoSetId;
    private PhotoSetAdapter mAdapter;
    private List<PhotoSetInfo.PhotosEntity> mPhotosEntities;
    private boolean mIsHideToolbar = false;

    public static void launch(Context context, String photoId) {
        Intent intent = new Intent(context, PhotoSetActivity.class);
        intent.putExtra(PHOTO_SET_KEY, photoId);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_photo_set;
    }

    @Override
    protected void initInjector() {
        mPhotoSetId = getIntent().getStringExtra(PHOTO_SET_KEY);
        DaggerPhotoSetComponent.builder()
                .photoSetModule(new PhotoSetModule(this, mPhotoSetId))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
//        initToolBar(mToolbar, true, "");
        initToolbar(mToolbar,true,"");
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(PhotoSetInfo photoSetBean) {
        List<String> imgUrls = new ArrayList<>();
        mPhotosEntities = photoSetBean.getPhotos();
        for (PhotoSetInfo.PhotosEntity entity : mPhotosEntities) {
            imgUrls.add(entity.getImgurl());
        }
        mAdapter = new PhotoSetAdapter(imgUrls,this);
        mVpPhoto.setAdapter(mAdapter);
        mVpPhoto.setOffscreenPageLimit(imgUrls.size());

        mTvCount.setText(mPhotosEntities.size() + "");
        mTvTitle.setText(photoSetBean.getSetname());
        mTvIndex.setText(1 + "/");
        mTvContent.setText(mPhotosEntities.get(0).getNote());

        mVpPhoto.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTvContent.setText(mPhotosEntities.get(position).getNote());
                mTvIndex.setText((position + 1) + "/");
            }
        });
        mAdapter.setTapListener(new PhotoSetAdapter.OnTapListener() {
            @Override
            public void onPhotoClick() {
                mIsHideToolbar = !mIsHideToolbar;
                if (mIsHideToolbar) {
                    mDragLayout.scrollOutScreen(300);
                    mToolbar.animate().translationY(-mToolbar.getBottom()).setDuration(300);
                } else {
                    mDragLayout.scrollInScreen(300);
                    mToolbar.animate().translationY(0).setDuration(300);
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_right_exit);
    }
}
