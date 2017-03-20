package com.amibtion.mvp.reader.local.dao;

import android.content.Context;

import com.amibtion.mvp.reader.local.table.DaoSession;
import com.amibtion.mvp.reader.local.table.NewsTypeInfo;
import com.amibtion.mvp.reader.local.table.NewsTypeInfoDao;
import com.amibtion.mvp.reader.utils.AssetsHelper;
import com.amibtion.mvp.reader.utils.GsonHelper;

import java.util.List;

/**
 * Created by nieyuxin on 2017/3/17.
 */

public class NewsTypeDao {

    private static List<NewsTypeInfo> sAllChannels;

    private NewsTypeDao() {}

    public static void updateLocalData(Context context,DaoSession daoSession){
        sAllChannels = GsonHelper.convertEntities(AssetsHelper.readData(context,"NewsChannel"),NewsTypeInfo.class);
        NewsTypeInfoDao beanDao = daoSession.getNewsTypeInfoDao();
        if (beanDao.count() == 0){
            beanDao.insertInTx(sAllChannels.subList(0,3));
        }
    }

    public static List<NewsTypeInfo> getAllChannels() {
        return sAllChannels;
    }
}
