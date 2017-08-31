package com.yqq.mydbstudy.dao;

import android.content.Context;

import com.yqq.mydbstudy.MyApplication;
import com.yqq.mydbstudy.dao.impl.UserDaoImpl;

public class DataInfoFactory {
	private static final String TAG = "DataInfoFactory";

	private DataInfoFactory() {

	}


	public static synchronized UserDao getUserDao(Context context,String type) {
		switch (type){
			case "user":
				if ( null== MyApplication.getInstance().mUserDao) {

					MyApplication.getInstance().mUserDao = new UserDaoImpl(context.getApplicationContext());
				}
				return 	MyApplication.getInstance().mUserDao;




		}

	return null;
	}
	

}
