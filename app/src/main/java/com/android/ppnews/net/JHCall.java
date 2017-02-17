package com.android.ppnews.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangyao on 17/2/17.
 */

public class JHCall {
    private static JHService service = null;
    public static JHService getService(){
        if(service==null){
            service =new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(JHService.URL)
                    .build().create(JHService.class);
        }
        return service;
    }
}
