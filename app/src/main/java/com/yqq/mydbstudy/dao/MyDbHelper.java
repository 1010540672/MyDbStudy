package com.yqq.mydbstudy.dao;

import android.content.Context;
import android.util.Log;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDbHelper";
    //数据库名称
    private static final  String DATABASE_NAME = "test.db";
    //数据库版本信息用于数据库升级
    private static int sVersion = 2;

    private static volatile  MyDbHelper sDbHelper;
    private String mTableName="user";//支持多账号登录动态即可

    /**
     * 单例模式获取数据库操作助手对象
     *
     * @param context
     * @return
     */
    public static MyDbHelper getInstance(Context context) {
        if (null == sDbHelper) {
            synchronized (MyDbHelper.class) {
                sDbHelper = new MyDbHelper(context.getApplicationContext());
            }
        }
        return sDbHelper;
    }

    private MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, sVersion);
        // TODO Auto-generated constructor stub

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
           try{
               //建表
               db.execSQL("CREATE table IF NOT EXISTS "
                       + mTableName
                       + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " //
                       + DatabaseFiled.COL_NAME
                       + " TEXT, " //
                       + DatabaseFiled.COL_CARDNUM
                       + " TEXT  UNIQUE, " //
                       + DatabaseFiled.COL_AGE
                       + " INTEGER, "//
                       + DatabaseFiled.COL_SEX
                       + " INTEGER  ,"//
                       + DatabaseFiled.COL_ADDR
                       + " TEXT ); ");//
               Log.e(TAG,"数据库建表成功");

               db.execSQL("ALTER TABLE  " + "" + mTableName + " ADD COLUMN "
                       + DatabaseFiled.COL_MARK + "  TEXT   ");
           } catch (Exception e){
               e.printStackTrace();
               Log.e(TAG,e.toString());
           }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            switch (oldVersion){
                case 1:
                    db.execSQL("ALTER TABLE  " + "" + mTableName + " ADD COLUMN "
                            + DatabaseFiled.COL_MARK + "  TEXT   ");
                    //建立索引

                    db.execSQL("CREATE INDEX IF NOT EXISTS _userIndex_ ON  "
                            + mTableName
                            + " ( "
                            + DatabaseFiled.COL_NAME
                            + "  ); ");//

                case 2:
                default:
                    break;
            }
            Log.e(TAG,"数据库升级成功");
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,e.toString());
        }
    }
}
