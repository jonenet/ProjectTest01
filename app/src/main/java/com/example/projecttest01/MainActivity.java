package com.example.projecttest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.projecttest01.dagger.DaggerMainComponent;
import com.example.projecttest01.dagger.MainModule;
import com.example.projecttest01.dagger.TestDaggerBean;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Inject
    TestDaggerBean bean;
    private static final String TAG = "MainActivity";


    public static final Class[] classes = new Class[]{
            CallBackActivity.class

    };
    @BindView(R.id.btn_rxCall_back)
    Button btnRxCallBack;
    @BindView(R.id.btn_rxCall_back_2)
    Button btnRxCallBack2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder().mainModule(new MainModule("name","gender")).build().inject(this);
        if (null != bean) {
            btnRxCallBack.setText(bean.getName());
            btnRxCallBack2.setText(String.valueOf(bean.getGender()));
        }

        String test = getString(R.string.test);
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
