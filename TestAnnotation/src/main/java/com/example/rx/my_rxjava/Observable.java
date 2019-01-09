package com.example.rx.my_rxjava;


/**
 * Created by tlh on 2017/5/9.
 */
public class Observable<T> {
    private final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        //call方法
        onSubscribe.call(subscriber);
    }

//    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
//        return create(new OnSubscribe<R>() { // 生成一个桥接的Observable和 OnSubscribe
//            @Override
//            public void call(Subscriber<? super R> subscriber) {
//                Observable.this.subscribe(new Subscriber<T>() { // 订阅上层的Observable
//                    @Override
//                    public void onCompleted() {
//                        subscriber.onCompleted();
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        subscriber.onError(t);
//                    }
//
//                    @Override
//                    public void onNext(T var1) {
//                        // 将上层的onSubscribe发送过来的Event，通过转换和处理，转发给目标的subscriber
//                        subscriber.onNext(transformer.call(var1));
//                    }
//                });
//            }
//        });
//    }

    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
        //调用map方法分析

        //1.创建Observable 传入MapOnSubscribe 将之前的 Observable 作为参数传入 MapOnSubscribe
        //2.当调用subscribe方法时，就会调用 MapOnSubscribe 的call方法
        //3.call方法调用的是原来 Observable 的 subscribe 方法，就会调用原来Observable.OnSubscribe的call方法
        //4.原来的 Observable.OnSubscribe的call方法传入的是MapSubscriber，并会调用我们手动调用的onNext，onError,onComplete方法
        //5.MapSubscriber传入最外层传入的Subscriber和转换器
        //6.最终在MapSubscriber里面进行转换，并将结果传入最外层的subscribe

        //重要的俩个类
        // 1.MapOnSubscribe 用于激发转换器 MapOnSubscribe 的订阅
        // 2.MapSubscribe 包装类，实际发生转换的类

        return create(new MapOnSubscribe<T, R>(this, transformer));
    }

    public Observable<T> subscribeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件生产切换到新的线程
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                final Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }

                    @Override
                    public void onError(final Throwable t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(t);
                            }
                        });
                    }

                    @Override
                    public void onNext(final T var1) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(var1);
                            }
                        });
                    }
                });
            }
        });
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }

    public interface Transformer<T, R> {
        R call(T from);
    }
}
