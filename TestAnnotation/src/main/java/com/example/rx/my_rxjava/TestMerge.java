package com.example.rx.my_rxjava;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * 作者:
 * 包名:      com.example.rx.my_rxjava
 * 工程名:    ProjectTest01
 * 时间:      2018/11/29
 * 说明:
 */
public class TestMerge {

    public static void main(String[] args) {

        Flowable<Long> source1 = Flowable.create(new FlowableOnSubscribe<Long>() {
            @Override
            public void subscribe(FlowableEmitter<Long> emitter) throws Exception {
                emitter.onNext(200L);
                emitter.onNext(200L);
                emitter.onError(new NullPointerException());
            }
        }, BackpressureStrategy.ERROR)
//                .intervalRange(3, 3, 1, 1, TimeUnit.SECONDS);
                ;
        Flowable<Long> source2 = Flowable.create(emitter -> {
            emitter.onNext(100L);
            emitter.onNext(100L);
            emitter.onNext(100L);
            emitter.onNext(100L);
            emitter.onNext(100L);
            emitter.onNext(100L);
        }, BackpressureStrategy.ERROR);


        Observable<String> observableOne = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onError(new NullPointerException(""));
            }
        });

        Observable<String> observableTwo = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("222");
                emitter.onNext("222");
                emitter.onNext("222");
                emitter.onNext("222");
                emitter.onNext("222");
                emitter.onNext("222");
                emitter.onNext("222");
            }
        });

        ObservableSource<String> observableSource1 = new ObservableSource<String>() {

            @Override
            public void subscribe(Observer<? super String> observer) {
                observer.onNext("observableSource1");
            }
        };
        ObservableSource<String> observableSource2 = new ObservableSource<String>() {

            @Override
            public void subscribe(Observer<? super String> observer) {
                observer.onNext("observableSource2");
            }
        };

//        Flowable.mergeArray(new Flowable<Integer>[]{Flowable<Integer>.just(1,2,3),Flowable<Integer>.just(4,5,6)}).subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println("error");
//            }
//        });

    }
}
