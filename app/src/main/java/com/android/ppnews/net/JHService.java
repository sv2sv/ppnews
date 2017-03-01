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

     String URL = "http://v.juhe.cn/";


    @GET("toutiao/index?")
    Call<JHNew> getData( @Query("type") String type,@Query("key")String key);

}
