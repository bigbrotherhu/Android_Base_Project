package com.android.base.who.dataCenter.source.repository;

import com.android.base.who.dataCenter.bean.LoginBean;

public interface LoginDataSource {

    boolean isLogin();

    LoginBean login(String username, String password);

}
