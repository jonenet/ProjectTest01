package com.example.daggerapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_name)
    TextView tvName;

    @Inject
    DaggerBean bean1;

    @Inject
    DaggerBean bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainComponent build = DaggerMainComponent.builder().build();
        build.inject(this);
        ClassActivityScope classActivityScope = new ClassActivityScope(build);
        DaggerBean bean3 = classActivityScope.getDaggerBean();
        Log.e(TAG, "bean1: " + bean1);
        Log.e(TAG, "bean2: " + bean2);
        Log.e(TAG, "bean3: " + bean3);
    }
}
