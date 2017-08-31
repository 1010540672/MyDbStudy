package com.yqq.mydbstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yqq.mydbstudy.bean.User;
import com.yqq.mydbstudy.dao.DataInfoFactory;
import com.yqq.mydbstudy.utils.ThreadPoolUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void  add(View view){
        if(null==MyApplication.getInstance().mUserDao){
            MyApplication.getInstance().mUserDao= DataInfoFactory.getUserDao(MainActivity.this,"user");
        }
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                User user=new User();
                user.mName="xiaoming";
                user.mSex="M";
                user.mAge="19";
                user.mAddr="测试地址";
                MyApplication.getInstance().mUserDao.addUser(user);
            }
        });


    }


    public void  read(View view){
        if(null==MyApplication.getInstance().mUserDao){
            MyApplication.getInstance().mUserDao= DataInfoFactory.getUserDao(MainActivity.this,"user");
        }
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
            List<User> users= MyApplication.getInstance().mUserDao.getUserInfo();
                for(User user:users){

                    Log.e("数据库读取","读取的数据===="+user.toString());
                }
            }
        });


    }



    public void  delete(View view){
        if(null==MyApplication.getInstance().mUserDao){
            MyApplication.getInstance().mUserDao= DataInfoFactory.getUserDao(MainActivity.this,"user");
        }




        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                        User user=new User();
                user.mName="xiaoming";
                MyApplication.getInstance().mUserDao.delUser(user);

                List<User> users= MyApplication.getInstance().mUserDao.getUserInfo();
                for(User user2:users){

                    Log.e("数据库读取","读取的数据===="+user2.toString());
                }
            }
        });

    }


    public void  modify(View view){

        if(null==MyApplication.getInstance().mUserDao){
            MyApplication.getInstance().mUserDao= DataInfoFactory.getUserDao(MainActivity.this,"user");
        }




        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                User user=new User();
                user.mName="xiaoming";
                user.mAddr="H哈哈哈哈啊哈哈哈哈爱好爱好";
                MyApplication.getInstance().mUserDao.modifyUser(user);

                List<User> users= MyApplication.getInstance().mUserDao.getUserInfo();
                for(User user2:users){

                    Log.e("数据库读取","读取的数据===="+user2.toString());
                }
            }
        });
    }


    public void  manyAadd(View view){
        if(null==MyApplication.getInstance().mUserDao){
            MyApplication.getInstance().mUserDao= DataInfoFactory.getUserDao(MainActivity.this,"user");
        }
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> infos=new ArrayList<User>();
                for(int i=0;i<200;i++){
                    User user=new User();
                    user.mName="Lihua"+i;
                    user.mSex="M";
                    user.mAge="19";
                    user.mAddr="测试地址"+i;
                    infos.add(user);
                }

                MyApplication.getInstance().mUserDao.addUser4Many(infos);
            }
        });


    }
}
