package com.yqq.mydbstudy.dao;

import com.yqq.mydbstudy.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public interface UserDao {

    boolean addUser(User user);
    boolean addUser4Many(List<User> users);
    List<User> getUserInfo();
    boolean delUser(User user);
    boolean modifyUser(User user);
}
