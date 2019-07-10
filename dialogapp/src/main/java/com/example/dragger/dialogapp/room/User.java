package com.example.dragger.dialogapp.room;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.dragger.dialogapp.room
 * ProjectName: ProjectTest01
 * Date: 2019/7/9 17:38
 */
@Entity(tableName = "user")
public class User {
    @PrimaryKey
    private int uid;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @Ignore
    Bitmap picture;//不进行持久化
    @ColumnInfo(name = "is_male")
    private int isMale;
    @ColumnInfo(name = "address")
    private String address;

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Bitmap getPicture() {
        return picture;
    }
    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public int getIsMale() {
        return isMale;
    }

    public void setIsMale(int isMale) {
        this.isMale = isMale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", picture=" + picture +
                ", isMale=" + isMale +
                ", address='" + address + '\'' +
                '}';
    }
}