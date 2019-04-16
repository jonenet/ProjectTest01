package com.example.projecttest01;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.projecttest01.annotation.AnnoGender;
import com.example.projecttest01.dagger.AnimalBean;
import com.example.projecttest01.dagger.DaggerMainComponent;
import com.example.projecttest01.dagger.MainModule;
import com.example.projecttest01.dagger.TestDaggerBean;
import com.example.projecttest01.subjectTest.MoreReactiveList;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {


    @Inject
    TestDaggerBean bean;

    @Inject
    TestDaggerBean bean2;

    @Inject
    AnimalBean animalBean;

    @Inject
    AnimalBean animalBean2;

    @AnnoGender
    @Inject
    String gender;
    private static final String TAG = "MainActivity";


    public static final Class[] classes = new Class[]{
            CallBackActivity.class

    };
    @BindView(R.id.btn_rxCall_back)
    Button btnRxCallBack;
    @BindView(R.id.btn_rxCall_back_2)
    Button btnRxCallBack2;
    @BindView(R.id.btn_rxCall_back_3)
    Button btnRxCallBack3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent
                .builder()
                .mainModule(new MainModule("name", "gender"))
                .build()
                .inject(this);
        if (null != bean) {
            btnRxCallBack.setText(bean.getName());
            btnRxCallBack2.setText(String.valueOf(bean.getGender()));
            Log.e(TAG, "bean: " + bean);
        }

        if (null != bean2) {
            Log.e(TAG, "bean2: " + bean2);
        }

        ReplaySubject.createWithSize(10);

        if (null != animalBean) {
            btnRxCallBack3.setText(animalBean.getHeight());
            Log.e(TAG, "animalBean: " + animalBean);
        }

        if (null != animalBean2) {
            Log.e(TAG, "animalBean2: " + animalBean2);
        }
        MoreReactiveList<Long> list = new MoreReactiveList<>();

        list.changes().subscribe(System.out::println);
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
