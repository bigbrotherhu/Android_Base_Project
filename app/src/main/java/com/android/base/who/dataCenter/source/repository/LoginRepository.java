package com.android.base.who.dataCenter.source.repository;

import com.android.base.who.dataCenter.bean.LoginBean;
import com.android.base.who.dataCenter.source.local.sp.LoginSpDataSource;
import com.android.base.who.dataCenter.source.remote.remoteDataSource.LoginRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginRepository implements LoginDataSource{

    private LoginSpDataSource mLoginSpDataSource;
    private LoginRemoteDataSource mLoginRemoteDataSource;

    private LoginRepository(){
        mLoginSpDataSource = new LoginSpDataSource();
        mLoginRemoteDataSource = new LoginRemoteDataSource();
    }

    //直接获取本地数据
    @Override
    public boolean isLogin() {
        return mLoginSpDataSource.isLogin();
    }
    //获取网络数据
    @Override
    public LoginBean login(String username, String password) {
        return mLoginRemoteDataSource.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
