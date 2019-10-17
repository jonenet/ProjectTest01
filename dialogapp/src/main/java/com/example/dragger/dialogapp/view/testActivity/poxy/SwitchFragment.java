package com.example.dragger.dialogapp.view.testActivity.poxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dragger.dialogapp.R;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.view.testActivity
 * ProjectName: ProjectTest01
 * Date: 2019/9/6 9:17
 */
public class SwitchFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvOne;
    private TextView mTvTwo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_switch, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        rootView.findViewById(R.id.btn_one).setOnClickListener(this);
        rootView.findViewById(R.id.btn_two).setOnClickListener(this);

        mTvOne = (TextView) rootView.findViewById(R.id.tv_text_one);
        mTvTwo = (TextView) rootView.findViewById(R.id.tv_text_two);
    }


    @Override
    public void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            String one = bundle.getString("one");
            String two = bundle.getString("two");
            mTvOne.setText(one);
            mTvTwo.setText(two);
        }
    }

    @Override
    public String onQuery() {
        return "";
    }

    @Override
    public void onExecute(Intent intent) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                break;
            case R.id.btn_two:
                break;
        }
    }
}
