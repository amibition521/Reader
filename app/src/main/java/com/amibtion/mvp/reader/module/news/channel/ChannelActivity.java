package com.amibtion.mvp.reader.module.news.channel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.injector.components.DaggerManageComponent;
import com.amibtion.mvp.reader.injector.modules.ChannelModule;
import com.amibtion.mvp.reader.local.table.NewsTypeInfo;
import com.amibtion.mvp.reader.module.base.BaseActivity;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnItemMoveListener;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.dl7.recycler.listener.OnRemoveDataListener;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by nieyuxin on 2017/3/24.
 */

public class ChannelActivity extends BaseActivity <IChannelPresenter> implements IChannelView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_checked_list)
    RecyclerView mRvCheckedList;
    @BindView(R.id.rv_unchecked_list)
    RecyclerView mRvUncheckedList;

    @Inject
    BaseQuickAdapter mCheckedAdapter;
    @Inject
    BaseQuickAdapter mUncheckedAdapter;


    public static void launch(Context context){
        Intent intent  = new Intent(context, ChannelActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.fade_entry, R.anim.hold);

    }



    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_channel;
    }

    @Override
    protected void initInjector() {
        DaggerManageComponent.builder()
                .applicationComponent(getAppComponent())
                .channelModule(new ChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolbar(mToolbar,true,"栏目管理");
        RecyclerViewHelper.initRecyclerViewG(this,mRvCheckedList,mCheckedAdapter,4);
        RecyclerViewHelper.initRecyclerViewG(this, mRvUncheckedList, mUncheckedAdapter, 4);
        RecyclerViewHelper.startDragAndSwipe(mRvCheckedList,mCheckedAdapter,3);

        mRvCheckedList.setItemAnimator(new ScaleInAnimator());
        mRvUncheckedList.setItemAnimator(new FlipInBottomXAnimator());
        // 设置拖拽背景
        mCheckedAdapter.setDragDrawable(ContextCompat.getDrawable(this, R.drawable.shape_channel_drag));
        // 设置移除监听器
        mCheckedAdapter.setRemoveDataListener(new OnRemoveDataListener() {
            @Override
            public void onRemove(int position) {
                mUncheckedAdapter.addLastItem(mCheckedAdapter.getItem(position));
                mPresenter.delete(mCheckedAdapter.getItem(position));
            }
        });
        mCheckedAdapter.setItemMoveListener(new OnItemMoveListener() {
            @Override
            public void onItemMove(int fromPosition, int toPosition) {
                mPresenter.update(mCheckedAdapter.getData());
                mPresenter.swap(fromPosition,toPosition);
            }
        });
        mUncheckedAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Object data = mUncheckedAdapter.getItem(position);
                mUncheckedAdapter.removeItem(position);
                mCheckedAdapter.addLastItem(data);
                mPresenter.insert(data);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    
    @Override
    public void loadData(List<NewsTypeInfo> checkList, List<NewsTypeInfo> uncheckList) {
        mCheckedAdapter.updateItems(checkList);
        mUncheckedAdapter.updateItems(uncheckList);
    }
	@Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.fade_exit);

    }
}
