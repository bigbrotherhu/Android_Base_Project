package com.android.base.who.dataCenter.source.remote.netServices;

import com.android.base.who.BuildConfig;
import com.android.base.who.dataCenter.source.remote.netServices.converter.StringConverterFactory;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by huchunjie on 2018/12/10.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper = null;

    private RetrofitService retrofitService = null;

    private RetrofitHelper(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(HttpsFactroy.getClient())
                .addConverterFactory(StringConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitHelper getInstance(){
        if(retrofitHelper == null){
            synchronized (RetrofitHelper.class){
                if(retrofitHelper == null){
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    public RetrofitService getRetrofitService(){
        return retrofitService;
    }
}
