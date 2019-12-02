package com.android.base.who.dataCenter.source.remote.netServices;

import com.android.base.who.dataCenter.bean.BaseResp;
import com.android.base.who.dataCenter.bean.LoginBean;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by huchunjie on 2018/12/10.
 */

public interface RetrofitService {

    @POST("/login")
    @FormUrlEncoded
    Observable<BaseResp<LoginBean>> login(@Field("username") String username,@Field("password") String password);

}
