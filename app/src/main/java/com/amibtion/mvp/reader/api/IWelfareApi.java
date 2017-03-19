package com.amibtion.mvp.reader.api;

import com.amibtion.mvp.reader.api.bean.WelfarePhotoList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

import static com.amibtion.mvp.reader.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by nieyuxin on 2017/3/19.
 */

public interface IWelfareApi {

    /**
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("/api/data/福利/10/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);
}
