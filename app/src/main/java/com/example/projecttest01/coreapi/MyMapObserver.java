package com.example.projecttest01.coreapi;

import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ex-zhoulai on 2018/3/30.
 */

public class MyMapObserver<T, R> implements ObservableOperator<R, T> {

    private Fun<T, R> fun;

    public MyMapObserver(Fun<T, R> fun) {
        this.fun = fun;
    }


    @Override
    public Observer<? super T> apply(final Observer<? super R> observer) throws Exception {

        return new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(T t) {
                R call = fun.call(t);
                observer.onNext(call);
            }

            @Override
            public void onError(Throwable t) {
                observer.onError(t);
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }
        };
    }
}
