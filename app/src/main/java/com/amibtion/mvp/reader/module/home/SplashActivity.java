package com.amibtion.mvp.reader.module.home;

import android.content.Intent;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.module.base.BaseActivity;
import com.amibtion.mvp.reader.utils.RxHelper;
import com.amibtion.mvp.reader.widget.SimpleButton;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by nieyuxin on 2017/3/21.
 * 引导页
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean mIsSkip = false;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSKip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSKip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过" + integer);
                    }
                });
    }

    private void _doSKip() {
        if (!mIsSkip){
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
            overridePendingTransition(R.anim.hold,R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        //不响应back键
        return;
    }

    @OnClick(R.id.sb_skip)
    public void onClick() {
        _doSKip();
    }
}
