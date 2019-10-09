package com.example.dragger.dialogapp.view.testActivity;

import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.view.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/9/6 15:17
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    public abstract void initData();

    public abstract String onQuery();

    public abstract void onExecute(Intent intent);

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
