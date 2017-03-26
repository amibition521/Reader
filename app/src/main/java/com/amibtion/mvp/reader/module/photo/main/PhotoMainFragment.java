package com.amibtion.mvp.reader.module.photo.main;

import android.animation.Animator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.adapter.ViewPagerAdapter;
import com.amibtion.mvp.reader.injector.components.DaggerPhotoMainComponent;
import com.amibtion.mvp.reader.injector.modules.PhotoMainModule;
import com.amibtion.mvp.reader.module.base.BaseFragment;
import com.amibtion.mvp.reader.module.base.IRxBusPresenter;
import com.amibtion.mvp.reader.module.manage.love.LoveActivity;
import com.amibtion.mvp.reader.module.photo.beauty.BeautyListFragment;
import com.amibtion.mvp.reader.module.photo.news.PhotoNewsFragment;
import com.amibtion.mvp.reader.module.photo.welfare.WelfareListFragment;
import com.amibtion.mvp.reader.rxbus.event.LoveEvent;
import com.amibtion.mvp.reader.utils.AnimateHelper;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by nieyuxin on 2017/3/25.
 */

public class PhotoMainFragment extends BaseFragment<IRxBusPresenter> implements IPhotoMainView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_count)
    TextView mIvCount;

    @Inject
    ViewPagerAdapter mPagerAdapter;
    private Animator mLovedAnimator;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_main;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoMainComponent.builder()
                .applicationComponent(getAppComponent())
                .photoMainModule(new PhotoMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, "图片");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BeautyListFragment());
        fragments.add(new WelfareListFragment());
        fragments.add(new PhotoNewsFragment());
        mPagerAdapter.setItems(fragments, new String[] {"美女", "福利", "生活"});
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
        mPresenter.registerRxBus(LoveEvent.class, new Action1<LoveEvent>() {
            @Override
            public void call(LoveEvent loveEvent) {
                mPresenter.getData(false);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void updateCount(int lovedCount) {
        mIvCount.setText(lovedCount+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLovedAnimator == null) {
            mIvCount.post(new Runnable() {
                @Override
                public void run() {
                    mLovedAnimator = AnimateHelper.doHappyJump(mIvCount, mIvCount.getHeight() * 2/3, 3000);
                }
            });
        } else {
            AnimateHelper.startAnimator(mLovedAnimator);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        AnimateHelper.stopAnimator(mLovedAnimator);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }

    @OnClick(R.id.fl_layout)
    public void onClick() {
        LoveActivity.launch(mContext, 0);
    }

}
