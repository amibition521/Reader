package com.amibtion.mvp.reader.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.utils.ImageLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by nieyuxin on 2017/3/18.
 * 图集 Adapter
 */

public class PhotoSetAdapter extends PagerAdapter {

    private List<String> mImgList;
    private Context mContext;
    private OnTapListener mTapListenr;

    public PhotoSetAdapter(List<String> mImgList, Context mContext) {
        this.mImgList = mImgList;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return mImgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_photo_pager,null,false);
        final PhotoView photo = (PhotoView) view.findViewById(R.id.iv_photo);
        final SpinKitView loadingView = (SpinKitView) view.findViewById(R.id.loading_view);
        final TextView tvReload = (TextView) view.findViewById(R.id.tv_reload);

        final RequestListener<String,GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                loadingView.setVisibility(View.GONE);
                tvReload.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                loadingView.setVisibility(View.GONE);
                tvReload.setVisibility(View.GONE);
                photo.setImageDrawable(resource);
                return true;
            }
        };
        ImageLoader.loadCenterCrop(mContext,mImgList.get(position % mImgList.size()),photo,requestListener);
        photo.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (mTapListenr != null){
                    mTapListenr.onPhotoClick();
                }
            }
        });
        tvReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvReload.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                ImageLoader.loadFitCenter(mContext,mImgList.get(position % mImgList.size()),photo,requestListener);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnTapListener{
        void onPhotoClick();
    }
}
