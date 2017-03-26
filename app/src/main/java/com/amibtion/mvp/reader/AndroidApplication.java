package com.amibtion.mvp.reader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.amibtion.mvp.reader.api.RetrofitService;
import com.amibtion.mvp.reader.engine.DownloaderWrapper;
import com.amibtion.mvp.reader.injector.components.ApplicationComponent;
import com.amibtion.mvp.reader.injector.components.DaggerApplicationComponent;
import com.amibtion.mvp.reader.injector.modules.ApplicationModule;
import com.amibtion.mvp.reader.local.dao.NewsTypeDao;
import com.amibtion.mvp.reader.local.table.DaoMaster;
import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.rxbus.RxBus;
import com.amibtion.mvp.reader.utils.DownloadUtils;
import com.amibtion.mvp.reader.utils.PreferencesUtils;
import com.amibtion.mvp.reader.utils.ToastUtils;
import com.dl7.downloaderlib.DownloadConfig;
import com.dl7.downloaderlib.FileDownloader;
import com.dl7.tinkerlib.Log.MyLogImp;
import com.dl7.tinkerlib.util.TinkerManager;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import retrofit2.Retrofit;

/**
 * Created by nieyuxin on 2017/3/12.
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.amibition.mvp.reader.Application",
    flags = ShareConstants.TINKER_ENABLE_ALL,
    loadVerifyFlag = false)
public class AndroidApplication extends DefaultApplicationLike {

    private static final String DB_NAME = "news-db";

    private static ApplicationComponent sAppComponent;
    private static Context sContext;

    private DaoSession mDaoSession;

    private RxBus mRxBus = new RxBus();

    public AndroidApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        sContext = getApplication();

        MultiDex.install(base);

        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();

        TinkerManager.setUpgradeRetryEnable(true);
        TinkerInstaller.setLogIml(new MyLogImp());
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());

    }
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback){
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _initDataBase();
        _initInjector();
        _initConfig();
    }


    public static Context getContext(){
        return sContext;
    }

    private void _initInjector(){
        sAppComponent = DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this,mDaoSession,mRxBus))
                        .build();
    }

    private void _initDataBase(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(),DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(getApplication(),mDaoSession);
        DownloadUtils.init(mDaoSession.getBeautyPhotoInfoDao());
    }

    private void _initConfig(){
        if (BuildConfig.DEBUG){
            LeakCanary.install(getApplication());
            Logger.init("LogTAG");
        }

        RetrofitService.init();
        ToastUtils.init(getApplication());
        DownloaderWrapper.init(mRxBus,mDaoSession.getVideoInfoDao());
        FileDownloader.init(getApplication());
        DownloadConfig config = new DownloadConfig.Builder().setDownloadDir(PreferencesUtils.getSavePath(getApplication()) + File.separator + "video/").build();
        FileDownloader.setConfig(config);

    }

    /**
     * 使用Tinker生成Application，这里改成静态调用
     * @return
     */
    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

}
