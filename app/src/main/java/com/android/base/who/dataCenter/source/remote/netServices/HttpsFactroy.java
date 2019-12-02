package com.android.base.who.dataCenter.source.remote.netServices;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by huchunjie on 2018/12/10.
 */

public class HttpsFactroy {

    //得到请求client
    public static OkHttpClient getClient(){
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(message -> Log.i("HTTPS","OkHttp====Message:"+message));
        loggingInterceptor.setLevel(level);
        OkHttpClient client = null;
        try {
            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new HeaderIntercepter())
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            Log.e("error", Log.getStackTraceString(e));
        }

        return client;
    }

    /**
     * 关闭持久连接
     */
    private static class HeaderIntercepter implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("Connection", "close")
            .build();
            return chain.proceed(request);
        }
    }
}
