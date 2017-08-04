package com.yqq.mydbstudy;

import android.app.Application;
import android.content.Intent;

import com.yqq.mydbstudy.dao.UserDao;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class MyApplication extends Application {

    private static MyApplication sApplication;
    public  UserDao mUserDao;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }


    public synchronized static MyApplication getInstance() {

        return sApplication;
    }
}
