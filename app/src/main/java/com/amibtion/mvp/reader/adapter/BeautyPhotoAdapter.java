package com.amibtion.mvp.reader.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.local.table.BeautyPhotoInfo;
import com.amibtion.mvp.reader.module.photo.bigphoto.BigPhotoActivity;
import com.amibtion.mvp.reader.utils.DefIconFactory;
import com.amibtion.mvp.reader.utils.ImageLoader;
import com.amibtion.mvp.reader.utils.StringUtils;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 * 美图 Adapter
 */

public class BeautyPhotoAdapter extends BaseQuickAdapter<BeautyPhotoInfo> {

    private int mPhotoWidth;
    private Fragment mFragment;

    public BeautyPhotoAdapter(Fragment fragment){
        this(fragment.getContext());
        mFragment = fragment;
    }

    public BeautyPhotoAdapter(Context context){
        super(context);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
    }

    public BeautyPhotoAdapter(Context context, List<BeautyPhotoInfo> data){
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_beauty_photos;
    }

    @Override
    protected void convert(final BaseViewHolder holder, BeautyPhotoInfo item) {
        final ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int photoHeight = StringUtils.calcPhotoHeight(item.getPixel(),mPhotoWidth);
        // 接口返回的数据有像素分辨率，根据这个来缩放图片大小
        final ViewGroup.LayoutParams params = ivPhoto.getLayoutParams();
        params.width = mPhotoWidth;
        params.height = photoHeight;
        ivPhoto.setLayoutParams(params);
        ImageLoader.loadFitCenter(mContext,item.getImgsrc(),ivPhoto, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title,item.getTitle());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFragment != null){
                    BigPhotoActivity.launchForResult (mFragment,(ArrayList<BeautyPhotoInfo>)getData(),holder.getAdapterPosition());
                } else {
                    BigPhotoActivity.launch(mContext,(ArrayList<BeautyPhotoInfo>)getData(),holder.getAdapterPosition());
                }
            }
        });
    }
}
