package com.android.ppnews.net;

import com.android.ppnews.pojo.Category;
import com.android.ppnews.pojo.Data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wangyao on 15/2/17.
 */

public interface PPService {
    @GET("news/categories")
    Call<Category> getCategories();
    @GET("news/details/{clink}?index={index}")
    Call<Data> getDatas(@Path("clink") String clink, @Path("index") int index);

}
