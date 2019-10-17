package com.example.dragger.dialogapp;

import java.util.ArrayList;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp
 * ProjectName: ProjectTest01
 * Date: 2019/10/16 11:54
 */

public class User implements java.io.Serializable {
    private static final long serialVersionUID = -2851024741429074888L;
    private String userName;
    private String passWord;
    private String userInfo;
    private ArrayList<User> friends;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userInfo='" + userInfo + '\'' +
                    ", friends=" + friends +
                '}';
    }
}