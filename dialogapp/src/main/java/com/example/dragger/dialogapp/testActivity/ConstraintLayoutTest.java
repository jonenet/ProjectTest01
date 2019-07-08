package com.example.dragger.dialogapp.testActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.dragger.dialogapp.myLifeRecycler.LifeRecyclerActivity;
import com.example.dragger.dialogapp.R;

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
    }
}
