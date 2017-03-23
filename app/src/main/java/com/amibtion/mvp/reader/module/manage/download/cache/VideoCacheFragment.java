package com.amibtion.mvp.reader.module.manage.download.cache;

import android.view.View;
import android.widget.TextView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.injector.modules.VideoCacheModule;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.module.base.BaseVideoDlFragment;
import com.amibtion.mvp.reader.module.base.ILocalPresenter;
import com.amibtion.mvp.reader.module.base.ILocalView;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.module.manage.download.DownloadActivity;
import com.dl7.downloaderlib.entity.FileInfo;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRemoveDataListener;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/23.
 */

public class VideoCacheFragment extends BaseVideoDlFragment<IRxBusPresenter> implements ILocalView<VideoInfo> {

    @BindView(R.id.default_bg)
    TextView mDefaultBg;

    @Override
    public void loadData(List<VideoInfo> dataList) {
        if (mDefaultBg.getVisibility() == View.VISIBLE){
            mDefaultBg.setVisibility(View.GONE);
        }
        mAdapter.updateItems(dataList);
    }

    @Override
    public void noData() {
        mDefaultBg.setVisibility(View.VISIBLE);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_download;
    }

    @Override
    protected void initInjector() {
        DaggerVideoCacheComponent.builder()
                .applicationComponent(getAppComponent())
                .videoCacheModule(new VideoCacheModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        RecyclerViewHelper.initRecyclerViewV(mContext,mRvVideoList,mAdapter);
        mRvVideoList.setItemAnimator(new SlideInLeftAnimator());
        mAdapter.setRemoveDataListener(new OnRemoveDataListener() {
            @Override
            public void onRemove(int position) {
                if (mAdapter.getItemCount() <= 1 &&
                        mDefaultBg.getVisibility() == View.GONE){
                    mDefaultBg.setVisibility(View.VISIBLE);
                    ((DownloadActivity)getActivity()).enableEditMode(false);
                }
            }
        });
        initItemLongClick();
        mPresenter.registerRxBus(FileInfo.class, new Action1<FileInfo>() {
            @Override
            public void call(FileInfo fileInfo) {
                mAdapter.updateDownload(fileInfo);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }
}
