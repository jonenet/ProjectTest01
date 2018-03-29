package com.example.projecttest01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.projecttest01.callback.CallBack;
import com.example.projecttest01.callback.Fun;
import com.example.projecttest01.helper.ApiHelper;
import com.example.projecttest01.helper.AysncJob;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ex-zhoulai on 2018/3/29.
 */

public class CallBackActivity extends Activity {

    private String TAG = "CallBackActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test_01)
    public void onViewClicked() {

//        ApiHelper.getMethod("1").map(new Fun<Integer, String>() {
//            @Override
//            public String call(Integer integer) {
//                return String.valueOf(integer);
//            }
//        }).flatMap(new Fun<String, AysncJob<Integer>>() {
//            @Override
//            public AysncJob<Integer> call(String s) {
//                return ApiHelper.getMethod(s);
//            }
//        }).start(new CallBack<Integer>() {
//            @Override
//            public void onSuccess(Integer success) {
//                Log.e(TAG, "onSuccess: "+success );
//            }
//        });
    }
}
