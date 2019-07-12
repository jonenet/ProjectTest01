package com.example.dragger.dialogapp.myLifeRecycler;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.trello.rxlifecycle3.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Desc: TODO
 * <p>
 * Author:
 * PackageName: com.example.dragger.dialogapp.myLifeRecycler
 * ProjectName: ProjectTest01
 * Date: 2019/7/8 17:07
 */

public class LifeRecyclerActivity extends AppCompatActivity {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这个就可以是我们的presenter
        MyObserver myObserver = new MyObserver(new CallBack() {
            @Override
            public void update(String str) {
                Toast.makeText(LifeRecyclerActivity.this, str, Toast.LENGTH_SHORT).show();
                Log.i("TAG===", str);
            }
        });
        Lifecycle lifecycle = getLifecycle();
        lifecycle.addObserver(myObserver);

        Disposable subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycle.bindUntilEvent(mLifecycleSubject, ActivityEvent.STOP))
                .compose(Live.bindLifecycle(this))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.w("TAG===", String.valueOf(aLong));
                    }
                });
        mLifecycleSubject.onNext(ActivityEvent.CREATE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
    }
}
