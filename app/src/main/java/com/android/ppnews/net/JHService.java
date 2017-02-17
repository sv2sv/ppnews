package com.android.ppnews.net;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.android.ppnews.pojo.JHNew;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangyao on 17/2/17.
 */

public interface JHService {
    @StringDef({
            JHNewsType.CJ,
            JHNewsType.GJ,
            JHNewsType.GN,
            JHNewsType.JS,
            JHNewsType.KJ,
            JHNewsType.SH,
            JHNewsType.SS,
            JHNewsType.TOP,
            JHNewsType.TY,
            JHNewsType.YL
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {}

     String URL = "http://v.juhe.cn/";

     String KEY = "0ce28d8acf22bf9396f4e6a475aded0b";


    @GET("toutiao/index?")
    Call<JHNew> getData(@Type @Query("type") String type,@Query("key")String key);

}
