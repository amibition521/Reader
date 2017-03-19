package com.amibtion.mvp.reader.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.utils.DefIconFactory;
import com.amibtion.mvp.reader.utils.ImageLoader;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/19.
 */

public class VideoLoveAdapter extends BaseQuickAdapter<VideoInfo> {

    public VideoLoveAdapter(Context context) {
        super(context);
    }

    public VideoLoveAdapter(Context context, List<VideoInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_video_love;
    }

    @Override
    protected void convert(BaseViewHolder holder, VideoInfo item) {
        ImageView ivThumb = holder.getView(R.id.iv_thumb);
        ImageLoader.loadFitCenter(mContext,item.getCover(),ivThumb, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_desc,item.getSectiontitle());
    }
}
