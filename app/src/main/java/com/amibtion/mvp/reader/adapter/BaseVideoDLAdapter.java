package com.amibtion.mvp.reader.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.local.table.VideoInfo;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.rxbus.event.VideoEvent;
import com.dl7.downloaderlib.entity.FileInfo;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.orhanobut.logger.Logger;

/**
 * Created by nieyuxin on 2017/3/18.
 * video下载适配器基类
 */

public abstract class BaseVideoDLAdapter extends BaseQuickAdapter<VideoInfo>{

    private static final int INVALID_POS = -1;

    protected boolean mIsEditMode = false;
    protected SparseBooleanArray mSparseItemChecked = new SparseBooleanArray();
    protected final RxBus mRxBus;

    public BaseVideoDLAdapter(Context context,RxBus rxBus){
        super(context);
        mRxBus = rxBus;
    }

    /**
     * 处理选中事件
     * @param position
     * @param isChecked
     */
    protected void _handleCheckedChanged(int position,boolean isChecked){
        if (position == INVALID_POS){
            Logger.i(position + "" + isChecked) ;
            return;
        }
        mSparseItemChecked.put(position,isChecked);
        int checkedCount = 0;
        int checkedStatus;
        for(int i = 0; i < getItemCount();i++){
            if (mSparseItemChecked.get(i,false)){
                checkedCount++;
            }
        }

        if (checkedCount == 0){
            checkedStatus = VideoEvent.CHECK_NONE;
        } else if (checkedCount == getItemCount()){
            checkedStatus = VideoEvent.CHECK_ALL;
        } else {
            checkedStatus = VideoEvent.CHECK_SOME;
        }

        mRxBus.post(new VideoEvent(checkedStatus));
    }

    public boolean isEditMode() {
        return mIsEditMode;
    }

    public void setEditMode(boolean editMode){
        mIsEditMode = editMode;
        if (!mIsEditMode){
            mSparseItemChecked.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 切换Item 的选中状态
     * @param position
     * @param holder
     */
    public void toggleItemChecked(int position, BaseViewHolder holder){
        boolean isChecked = mSparseItemChecked.get(position);
        Logger.d(position + "--" + isChecked);
        holder.setChecked(R.id.cb_delete,!isChecked);
        _handleCheckedChanged(position,!isChecked);

        // 如果用 notifyItemChanged()，会有一闪的情况
        // notifyItemChanged(position);
    }

    public void deleteItemChecked(){
        for(int i = mSparseItemChecked.size() -1;i >= 0;i--){
            if(mSparseItemChecked.valueAt(i)){
                DownloaderWrapper.deleter(getItem(mSparseItemChecked.keyAt(i)));
                removeItem(mSparseItemChecked.keyAt(i));
                mSparseItemChecked.delete(mSparseItemChecked.keyAt(i));
            }
        }
    }

    public void checkAllOrNone(boolean isChecked){
        for(int i = 0; i < getItemCount(); i++){
            mSparseItemChecked.put(i,isChecked);
        }
        notifyDataSetChanged();
    }

    public void updateDownload(FileInfo fileInfo){

    }
}
