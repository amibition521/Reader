package com.amibtion.mvp.reader.module.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.adapter.BaseVideoDLAdapter;
import com.amibtion.mvp.reader.module.manage.download.DownloadActivity;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.listener.OnRecyclerViewItemLongClickListener;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by nieyuxin on 2017/3/18.
 * video 下载的基类Fragment
 */

public abstract class BaseVideoDLFragment<T extends IBasePresenter> extends BaseFragment<T> {

    @BindView(R.id.rv_video_list)
    protected RecyclerView mRvVideoList;
    @Inject
    protected BaseVideoDLAdapter mAdapter;

    private int mEditLayoutHeight;

    public void initItemLongClick(){
        mEditLayoutHeight = getResources().getDimensionPixelSize(R.dimen.edit_layout_height);
        mAdapter.setOnItemLongClickListener(new OnRecyclerViewItemLongClickListener() {

            @Override
            public boolean onItemLongClick(View view, int position) {
                if (!mAdapter.isEditMode()){
                    mAdapter.setEditMode(true);
                    ((DownloadActivity)getActivity()).enableEditMode(true);
                    mRvVideoList.setPadding(0,0,0,mEditLayoutHeight);
                }

                // 这里获取对应position对应的ViewHolder,需要借助RecyclerView，还有个更简便的做法是自定义的点击事件把ViewHolder一起传过来
                BaseViewHolder viewHolder = (BaseViewHolder) mRvVideoList.getChildViewHolder(view);
                if (viewHolder != null){
                    mAdapter.toggleItemChecked(position,viewHolder);

                }
                return true;
            }
        });
    }

    /**
     * 处理后退键
     * @return
     */
    public boolean exitEditMode(){
        if (mAdapter.isEditMode()){
            mAdapter.setEditMode(false);
            mRvVideoList.setPadding(0,0,0,0);
            return true;
        }
        return false;
    }

    /**
     * 是否处于编辑模式
     * @return
     */
    public boolean isEditMode() {
        return mAdapter.isEditMode();
    }

    /**
     * 全选或者取消全选
     * @param isChecked
     */
    public void checkAllOrNone(boolean isChecked){
        mAdapter.checkAllOrNone(isChecked);
    }

    /**
     * 删除选中
     */
    public void deleterChecked() {
        mAdapter.deleteItemChecked();
    }
}
