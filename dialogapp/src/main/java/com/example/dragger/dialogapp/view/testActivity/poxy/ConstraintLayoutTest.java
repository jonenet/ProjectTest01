package com.example.dragger.dialogapp.view.testActivity.poxy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.dragger.dialogapp.R;
import com.example.dragger.dialogapp.rx.myLifeRecycler.LifeRecyclerActivity;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.view.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/6/26 9:48
 */
public class ConstraintLayoutTest extends LifeRecyclerActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout_layout);
//        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        List<User> users = DBManager.getInstance().getUserDatabase().userDao().loadAllByIds(new int[]{10004});
////                        if (null != users && users.size() > 0) {
////                            Log.i("TAG", users.toString());
////                        }
////                    }
////                }).start();
//            }
//        });
        findViewById(R.id.start_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ConstraintLayoutTest", "CLICK");
                Intent intent = new Intent("com.lz.local.manage");
                intent.setPackage("com.lzui.launcher");
                intent.putExtra("system_key", "23");
                startService(intent);


//                User user2 = new User();
//                user2.setFirstName("jone2");
//                user2.setLastName("net2");
//                user2.setPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                user2.setUid(10002);
//
//                User user3 = new User();
//                user3.setFirstName("jone3");
//                user3.setLastName("net3");
//                user3.setPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                user3.setUid(10003);


//                User user4 = new User();
//                user4.setFirstName("jone4");
//                user4.setLastName("net4");
//                user4.setPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                user4.setUid(10004);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        DBManager.getInstance().getUserDatabase().userDao().insertAll(user2, user3);
//                        DBManager.getInstance().getUserDatabase().userDao().insertAll(user4);
//                    }
//                }).start();
            }
        });

        MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

        mElapsedTime.postValue(1L);
        mElapsedTime.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.i("ConstraintLayoutTest", "onChanged aLong = " + aLong);
            }
        });


    }
}
