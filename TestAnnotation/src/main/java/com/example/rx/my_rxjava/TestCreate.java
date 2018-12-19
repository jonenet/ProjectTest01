package com.example.rx.my_rxjava;

import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import sun.rmi.runtime.Log;

/**
 * 作者:      周来
 * 包名:      com.example.rx.my_rxjava
 * 工程名:    ProjectTest01
 * 时间:      2018/11/29
 * 说明:
 */
public class TestCreate {
    static String str1 = null;

    public static void main(String[] args) {
//        Flowable.range(0, 5)
//                .subscribe(x -> System.out.println(String.valueOf(x)));


//        Flowable.just(3,4,5)
//                .first(2)//这里的2是默认元素，非第二个元素
//                .subscribe(ele -> System.out.println(String.valueOf(ele)));
        //订阅第二个观察者
//
//        Flowable.just("a","b","c")
//                .elementAtOrError(3)//指定索引为2的元素，如果不存在则直接完成
//                .subscribe(ele -> System.out.println(String.valueOf(ele)));

//        Flowable.just("a","b","c")
//                .skip(1)
//                .skipLast(1)
//                .subscribe(ele ->System.out.println(String.valueOf(ele)));

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("111");
                emitter.onComplete();
//                        emitter.onError(new NullPointerException());
            }
        }, BackpressureStrategy.ERROR)
                .ignoreElements()
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("com");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("error");
                    }
                });


    }

    //    static Flowable<String> flowable = Flowable.defer(new Callable<Publisher<String>>() {
//        @Override
//        public Publisher<String> call() throws Exception {
//            return Flowable.just("1", "2");
//        }
//    });


}
