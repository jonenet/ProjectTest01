package com.example.projecttest01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.projecttest01.coreapi.Fun;
import com.example.projecttest01.coreapi.MyMapObserver;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ex-zhoulai on 2018/3/29.
 */

public class CallBackActivity extends Activity {

    private String TAG = "CallBackActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test_01)
    public void onViewClicked() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).lift(new MyMapObserver<String, String>(new Fun<String, String>() {
            @Override
            public String call(String s) {
                return s;
            }
        })).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Scheduler scheduler = Schedulers.newThread();


//        Observable.empty()
//        Observable.never()
//        Observable.fromArray().subscribe()
//        Observable.range(1,3)
//        Observable.interval()
//        Observable.timer()


//        ApiHelper.getMethod("1").map(new Fun<Integer, String>() {
//            @Override
//            public String call(Integer integer) {
//                return String.valueOf(integer);
//            }
//        }).flatMap(new Fun<String, AysncJob<Integer>>() {
//            @Override
//            public AysncJob<Integer> call(String s) {
//                return ApiHelper.getMethod(s);
//            }
//        }).start(new CallBack<Integer>() {
//            @Override
//            public void onSuccess(Integer success) {
//                Log.e(TAG, "onSuccess: "+success );
//            }
//        });
    }
}
