package com.android.base.who.dataCenter.source.remote.remoteDataSource;

import com.android.base.who.dataCenter.bean.BaseResp;
import com.android.base.who.dataCenter.bean.LoginBean;
import com.android.base.who.dataCenter.source.remote.netServices.RetrofitHelper;
import com.android.base.who.dataCenter.source.remote.netServices.RetrofitService;

import io.reactivex.Observable;

/**
 * 网络数据资源
 */
public class LoginRemoteDataSource {

    private RetrofitService retrofitService;

    public LoginRemoteDataSource(){
        retrofitService = RetrofitHelper.getInstance().getRetrofitService();
    }

    public Observable<BaseResp<LoginBean>> login(String username, String password){
        return retrofitService.login(username,password);
    }

}
