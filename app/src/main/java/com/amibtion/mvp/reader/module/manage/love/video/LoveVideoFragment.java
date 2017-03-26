package com.amibtion.mvp.reader.module.manage.love.video;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.injector.components.DaggerLoveVideoComponent;
import com.amibtion.mvp.reader.injector.modules.LoveVideoModule;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.module.base.BaseFragment;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.base.ILocalView;
import com.amibtion.mvp.reader.module.video.player.VideoPlayerActivity;
import com.amibtion.mvp.reader.utils.DialogHelper;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.dl7.recycler.listener.OnRecyclerViewItemLongClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by nieyuxin on 2017/3/22.
 * Video Love
 */

public class LoveVideoFragment extends BaseFragment<ILocalPresenter> implements ILocalView<VideoInfo> {


    @BindView(R.id.rv_love_list)
    RecyclerView mRvVideoList;
    @BindView(R.id.default_bg)
    TextView mDefaultBg;

    @Inject
    BaseQuickAdapter mAdapter;

    private int mCurIndex;


    @Override
    public void loadData(List<VideoInfo> videoList) {
        if (mDefaultBg.getVisibility() == View.VISIBLE){
            mDefaultBg.setVisibility(View.GONE);
        }
        mAdapter.updateItems(videoList);
    }

    @Override
    public void noData() {
        mDefaultBg.setVisibility(View.VISIBLE);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_love_list;
    }

    @Override
    protected void initInjector() {
        DaggerLoveVideoComponent.builder()
                .applicationComponent(getAppComponent())
                .loveVideoModule(new LoveVideoModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        SlideInRightAnimationAdapter slideAdapter = new SlideInRightAnimationAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext,mRvVideoList,slideAdapter);
        mRvVideoList.setItemAnimator(new SlideInLeftAnimator());
        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCurIndex = position;
                VideoPlayerActivity.launchForResult(LoveVideoFragment.this, (VideoInfo) mAdapter.getItem(position));
            }
        });
        mAdapter.setOnItemLongClickListener(new OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                DialogHelper.deleteDialog(mContext, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete(mAdapter.getItem(position));
                        mAdapter.removeItem(position);
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }
}
