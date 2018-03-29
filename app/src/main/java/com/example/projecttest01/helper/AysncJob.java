package com.example.projecttest01.helper;

import android.util.Log;

import com.example.projecttest01.callback.CallBack;
import com.example.projecttest01.callback.Fun;

/**
 * Created by ex-zhoulai on 2018/3/29.
 */

public abstract class AysncJob<T> {

    private final AysncJob<T> source = this;

    public abstract void start(CallBack<T> callBack);

    public <R> AysncJob<R> map(final Fun<T, R> fun) {
        return new AysncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onSuccess(T success) {
                        R call = fun.call(success);
                        callBack.onSuccess(call);
                    }
                });
            }
        };
    }

    public <R> AysncJob<R> flatMap(final Fun<T, AysncJob<R>> fun) {
        return new AysncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {

                source.start(new CallBack<T>() {
                    @Override
                    public void onSuccess(T success) {
                        AysncJob<R> call = fun.call(success);
                        call.start(new CallBack<R>() {
                            @Override
                            public void onSuccess(R success) {
                                callBack.onSuccess(success);
                            }
                        });
                    }
                });
            }
        };

    }
}
