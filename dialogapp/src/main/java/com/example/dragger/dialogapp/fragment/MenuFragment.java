package com.example.dragger.dialogapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.dragger.dialogapp.R;

import androidx.core.view.ViewCompat;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.fragment
 * ProjectName: ProjectTest01
 * Date: 2018/12/20 18:36
 */
public class MenuFragment extends Fragment {

    protected Interpolator mInterpolator = new DecelerateInterpolator();
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_menu, container, false);
//        mRootView.findViewById(R.id.btn_add_next).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewCompat.animate(mRootView)
                .translationX(0)
                .setDuration(1000)
                .setInterpolator(mInterpolator)
                .start();
    }


    interface OnMenuClick {
        void onClick();
    }
}
