package com.example.dragger.dialogapp.view.testActivity.poxy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dragger.dialogapp.R;
import com.example.dragger.dialogapp.view.testActivity.poxy.AirFragment;
import com.example.dragger.dialogapp.view.testActivity.poxy.BaseFragment;
import com.example.dragger.dialogapp.view.testActivity.poxy.SwitchFragment;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.view.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/9/6 9:11
 */
public class FragmentProxyActivity extends FragmentActivity {

    private FragmentManager mFragmentManager = null;
    private BaseFragment mCurrentFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_switch);
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        onNewIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (null != intent) {
            String name = intent.getStringExtra("name");
            BaseFragment fragment = getFragment(name);
            if (null != fragment) {
                Bundle bundle = new Bundle();
                bundle.putString("one", intent.getStringExtra("one"));
                bundle.putString("two", intent.getStringExtra("two"));
                fragment.setArguments(bundle);
                switchContent(mCurrentFragment, fragment);
            }
        }
    }


    public synchronized void switchContent(BaseFragment from, BaseFragment to) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (from != null) {
            if (from != to) {
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(from).add(R.id.ll_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
                }
                mCurrentFragment = to;
            } else {
                if (to.isHidden()) {
                    transaction.show(to).commit();
                }
            }
        } else {
            if (!to.isAdded()) {
                transaction.add(R.id.ll_content, to).commit();
            } else {
                transaction.show(to).commit();
            }
            mCurrentFragment = to;
        }
    }

    private HashMap<String, BaseFragment> fragments = new HashMap<>();
    private String[] devices = new String[]{"switch", "air"};


    public BaseFragment getFragment(String name) {
        if (!Arrays.asList(devices).contains(name)) {
            return null;
        }
        BaseFragment fragment = fragments.get(name);
        if (null == fragment) {
            switch (name) {
                case "switch":
                    fragment = new SwitchFragment();
                    break;
                case "air":
                    fragment = new AirFragment();
                    break;
            }
            fragments.put(name, fragment);
        }
        return fragment;
    }
}
