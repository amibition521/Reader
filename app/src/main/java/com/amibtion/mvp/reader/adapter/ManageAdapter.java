package com.amibtion.mvp.reader.adapter;

import android.content.Context;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.local.table.NewsTypeInfo;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/18.
 * 管理界面适配器
 */

public class ManageAdapter extends BaseQuickAdapter<NewsTypeInfo> {


    public ManageAdapter(Context context) {
        super(context);
    }

    public ManageAdapter(Context context, List<NewsTypeInfo> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_manage;
    }

    @Override
    protected void convert(BaseViewHolder holder, NewsTypeInfo item) {
        holder.setText(R.id.tv_channel_name,item.getName());
    }
}
