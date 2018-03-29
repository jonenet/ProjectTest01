package com.example.projecttest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    public static final Class[] classes = new Class[]{
            CallBackActivity.class

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_rxCall_back, R.id.btn_rxCall_back_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rxCall_back:
                startActivity(new Intent(this, classes[0]));
                break;
            case R.id.btn_rxCall_back_2:
                break;
        }
    }
}
