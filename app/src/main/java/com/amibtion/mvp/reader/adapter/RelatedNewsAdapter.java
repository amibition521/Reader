package com.amibtion.mvp.reader.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.api.bean.NewsItemInfo;
import com.amibtion.mvp.reader.utils.DefIconFactory;
import com.amibtion.mvp.reader.utils.ImageLoader;
import com.amibtion.mvp.reader.utils.StringUtils;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 * 相关新闻的适配器
 */

public class RelatedNewsAdapter extends BaseQuickAdapter<NewsItemInfo> {
    public RelatedNewsAdapter(Context context) {
        super(context);
    }

    public RelatedNewsAdapter(Context context, List<NewsItemInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_news_list;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsItemInfo item) {
        ImageView newsIcon = holder.getView(R.id.iv_icon);
        ImageLoader.loadCenterCrop(mContext,item.getImgsrc(),newsIcon, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_source, StringUtils.clipNewsSource(item.getSource()))
                .setText(R.id.tv_time,item.getPtime());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NewsDetailActivity.launch(mContext, item.getId());

            }
        });
    }
}
