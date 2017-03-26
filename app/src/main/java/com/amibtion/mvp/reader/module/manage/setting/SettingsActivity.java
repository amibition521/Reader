package com.amibtion.mvp.reader.module.manage.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.amibtion.mvp.reader.R;
import com.amibtion.mvp.reader.module.base.BaseSwipeBackActivity;

import butterknife.BindView;

/**
 * Created by nieyuxin on 2017/3/21.
 */

public class SettingActivity extends BaseSwipeBackActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public  static void launch(Context context){
        Intent intent = new Intent(context,SettingActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry,R.anim.hold);
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initToolbar(mToolbar,true,"设置");
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        getFragmentManager().beginTransaction().replace(R.id.settings_view,new SettingFragment()).commit();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold,R.anim.slide_right_exit);
    }
}
