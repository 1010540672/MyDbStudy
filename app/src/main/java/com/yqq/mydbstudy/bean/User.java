package com.yqq.mydbstudy.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class User implements Serializable {

    public String mName;
    public  String mAge;
    public  String mSex;
    public String mCardNum;
    public String mAddr;
    public  String mMark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mName != null ? !mName.equals(user.mName) : user.mName != null) return false;
        if (mAge != null ? !mAge.equals(user.mAge) : user.mAge != null) return false;
        if (mSex != null ? !mSex.equals(user.mSex) : user.mSex != null) return false;
        if (mCardNum != null ? !mCardNum.equals(user.mCardNum) : user.mCardNum != null)
            return false;
        if (mAddr != null ? !mAddr.equals(user.mAddr) : user.mAddr != null) return false;
        return mMark != null ? mMark.equals(user.mMark) : user.mMark == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mAge != null ? mAge.hashCode() : 0);
        result = 31 * result + (mSex != null ? mSex.hashCode() : 0);
        result = 31 * result + (mCardNum != null ? mCardNum.hashCode() : 0);
        result = 31 * result + (mAddr != null ? mAddr.hashCode() : 0);
        result = 31 * result + (mMark != null ? mMark.hashCode() : 0);
        return result;
    }
}
