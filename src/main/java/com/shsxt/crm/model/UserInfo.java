package com.shsxt.crm.model;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 14:59
 */

public class UserInfo {
    private String userIdStr;//id 的加密字符串
    private String userName;
    private String userPwd;

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
