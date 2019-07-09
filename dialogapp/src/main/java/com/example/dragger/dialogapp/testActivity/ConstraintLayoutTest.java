package com.example.dragger.dialogapp.testActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.dragger.dialogapp.R;
import com.example.dragger.dialogapp.myLifeRecycler.LifeRecyclerActivity;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/6/26 9:48
 */
public class ConstraintLayoutTest extends LifeRecyclerActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout_layout);
        findViewById(R.id.start_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ConstraintLayoutTest", "CLICK");
                Intent intent = new Intent("com.lz.local.manage");
                intent.setPackage("com.lzui.launcher");
                intent.putExtra("system_key", "23");
                startService(intent);
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
