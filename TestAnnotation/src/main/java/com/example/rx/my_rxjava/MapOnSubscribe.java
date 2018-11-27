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
        source.subscribe(new MapSubscriber<R, T>(subscriber, transformer));
    }
}
