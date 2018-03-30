package com.example.projecttest01.helper;

import com.example.projecttest01.callback.CallBack;
import com.example.projecttest01.coreapi.Fun;

/**
 * Created by ex-zhoulai on 2018/3/29.
 *
 */

public abstract class AysncJob<T> {

    private final AysncJob<T> source = this;

    public abstract void start(CallBack<T> callBack);

    public <R> AysncJob<R> map(final Fun<T, R> fun) {

        //AysncJob 其实就是一个能装载返回结果的任务
        //我们需要实现fun的 call 转换方法，把转换后的结果通过任务包装返回回去
        //这样就实现了一次任务交换
        return new AysncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                //这个source是谁调用了flatMap就是谁
                source.start(new CallBack<T>() {
                    @Override
                    public void onSuccess(T success) {
                        //真正执行的是call方法，用于转换数据
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
