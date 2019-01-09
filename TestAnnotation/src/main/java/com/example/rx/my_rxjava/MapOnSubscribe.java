package com.example.rx.my_rxjava;

/**
 * Created by tlh on 2017/5/9.
 */
public class MapOnSubscribe<T, R> implements Observable.OnSubscribe<R> {
    private final Observable<T> source;
    private final Observable.Transformer<? super T, ? extends R> transformer;

    public MapOnSubscribe(Observable<T> source, Observable.Transformer<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
}

    @Override
    public void call(Subscriber<? super R> subscriber) {
        //call方法

        // subscriber 最终调用的 Subscriber，输出结果
        // subscribe 会调用 OnSubscribe 的onNext方法
        // 此时调用的是 MapSubscriber 的onNext方法
        // MapSubscriber 传入的是输出结果的 subscriber 最终结果输出

        //TODO subscribe 这里使用了一个包装对象，调用包装对象的onNext方法对调用到subscriber的的onNext方法，
        //TODO 而且是实际传入值得onNext方法就是这个代理对象MapSubscriber
        source.subscribe(new MapSubscriber<R, T>(subscriber, transformer));
    }
}
