package com.yqq.mydbstudy.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.yqq.mydbstudy.bean.User;
import com.yqq.mydbstudy.dao.DatabaseFiled;
import com.yqq.mydbstudy.dao.MyDbHelper;
import com.yqq.mydbstudy.dao.UserDao;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class UserDaoImpl implements UserDao {
    private static final  String TAG="UserDaoImpl";
    private String mTableName="_user";
    private MyDbHelper myDbHelper;
    public UserDaoImpl(Context ctx){
        myDbHelper=MyDbHelper.getInstance(ctx);
        myDbHelper.setWriteAheadLoggingEnabled(true);//支持并发
    }
    @Override
    public boolean addUser(User user) {
        SQLiteDatabase database = null;
        try{
            database = myDbHelper.getWritableDatabase();
            if(database.isOpen()){

                int sex = user.mSex.equals("M") ? 1 : 0;
                String sql="INSERT INTO "+mTableName+"("+ DatabaseFiled.COL_NAME+","+DatabaseFiled.COL_AGE+","+
                        DatabaseFiled.COL_CARDNUM+","+
                        DatabaseFiled.COL_SEX+","
                        +DatabaseFiled.COL_ADDR+")VALUES(?,?,?,?,?)";
                database.execSQL(sql,new Object[]{user.mName,Integer.valueOf(user.mAge),user.mCardNum,sex,user.mAddr});

                Log.e(TAG,"数据插入成功");
                return true;
            }

            return false;
        }catch (Exception e){
          e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        }


    }

    @Override
    public boolean addUser4Many(List<User> users) {
        SQLiteDatabase database = null;
        try{
            database = myDbHelper.getWritableDatabase();
            database.beginTransaction();
            if(database.isOpen()){
                String sql="INSERT INTO "+mTableName+"("+ DatabaseFiled.COL_NAME+","+DatabaseFiled.COL_AGE+","+
                        DatabaseFiled.COL_CARDNUM+","+
                        DatabaseFiled.COL_SEX+","
                        +DatabaseFiled.COL_ADDR+")VALUES(?,?,?,?,?)";
              for(User user:users){
                  int sex = user.mSex.equals("M") ? 1 : 0;
                  database.execSQL(sql,new Object[]{user.mName,Integer.valueOf(user.mAge),user.mCardNum,sex,user.mAddr});

              }
                database.setTransactionSuccessful();
                Log.e(TAG,"数据批量操作完成");
                return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        }finally {
            if(null!=database){
                database.endTransaction();
            }
        }
    }

    @Override
    public List<User> getUserInfo() {
        SQLiteDatabase database = null;
        Cursor cursor=null;
        try{
            database = myDbHelper.getReadableDatabase();
            if(database.isOpen()){

                String sql="SELECT "+DatabaseFiled.COL_NAME+","+DatabaseFiled.COL_AGE+","+DatabaseFiled.COL_ADDR+","+DatabaseFiled.COL_SEX+"  FROM "+mTableName;
                cursor= database.rawQuery(sql,null);
                User user=null;
                ArrayList<User> users=new ArrayList<>();
                while (cursor.moveToNext()) {

                    String name = cursor.getString(cursor
                            .getColumnIndex(DatabaseFiled.COL_NAME));
                    String age = cursor.getString(cursor
                            .getColumnIndex(DatabaseFiled.COL_AGE));
                    String addr = cursor.getString(cursor
                            .getColumnIndex(DatabaseFiled.COL_ADDR));
                    String sex = cursor.getString(cursor
                            .getColumnIndex(DatabaseFiled.COL_SEX));
                    user=new User();
                    user.mName=name;
                    user.mAge=age;
                    user.mAddr=addr;
                    user.mSex=sex;
                    users.add(user);
                    user=null;


                }

                return users;
            }

            return null;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return null;
        }finally {
            if(null!=cursor){
                cursor.close();

            }
        }
    }

    @Override
    public boolean delUser(User user) {
        SQLiteDatabase database = null;
        try{
            database = myDbHelper.getWritableDatabase();
            if(database.isOpen()){
                String sql="DELETE FROM  "+mTableName +" WHERE  "+DatabaseFiled.COL_NAME+"=?";
                database.execSQL(sql,new String[]{user.mName});

                return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        }
    }

    /**
     * 根据用户名修改地址和年龄
     * @param user
     * @return
     */
    @Override
    public boolean modifyUser(User user) {
        SQLiteDatabase database = null;
        try{
            database = myDbHelper.getWritableDatabase();
            if(database.isOpen()){
                String sql="UPDATE  "+mTableName +" SET  "+DatabaseFiled.COL_ADDR+"=?,"+DatabaseFiled.COL_AGE+"=?" +"  WHERE  "+DatabaseFiled.COL_NAME+"=?";
                database.execSQL(sql,new String[]{user.mAddr,user.mAge});

                return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        }
    }
}
