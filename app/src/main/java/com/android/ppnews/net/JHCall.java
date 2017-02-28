package com.android.ppnews.net;

import com.android.ppnews.pojo.JHNew;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangyao on 17/2/17.
 */

public class JHCall {
    private static List<JHNew.ResultBean.DataBean> datas;
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

    public static List<JHNew.ResultBean.DataBean> getDa(String type) {
        JHCall.getService().getData(type, JHService.KEY).enqueue(new Callback<JHNew>() {
            @Override
            public void onResponse(Call<JHNew> call, Response<JHNew> response) {
                datas = response.body().getResult().getData();
            }

            @Override
            public void onFailure(Call<JHNew> call, Throwable t) {

            }
        });

        return datas;
    }
}
