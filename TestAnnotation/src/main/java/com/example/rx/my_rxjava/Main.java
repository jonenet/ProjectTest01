package com.example.rx.my_rxjava;

/**
 * Created by tlh on 2017/5/9.
 */
public class Main {
    public static void main(String[] args) {

        int i = 0x10000000 | 0x00060000;
        System.out.println(i);
        System.out.println(String.format("not support param: %08X", i));

//        Maybe.just(1).subscribe()
//        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                for (int i = 0; ; i++) {   //无限循环发事件
//                    emitter.onNext(i);
//                }
//
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//
//        Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
//                emitter.requested();
//                emitter.onNext("111");
//                System.out.println("111");
//                emitter.onNext("222");
//                System.out.println("222");
//                emitter.onNext("333");
//                System.out.println("333");
//                emitter.onNext("444");
//                System.out.println("444");
//            }
//        }, BackpressureStrategy.ERROR).subscribe(new org.reactivestreams.Subscriber<String>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                s.request(1000);
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("onNext = " + s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
////MissingBackpressureException
//                System.out.println( "onError: "+ t);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
////                .subscribe(new Consumer<Integer>() {
////
////            @Override
////            public void accept(Integer integer) throws Exception {
////                try {
////                    Thread.sleep(2000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                System.out.println("result = " + integer);
////            }
////        });
//
//
////        Observable Observable.OnSubscribe Subscriber
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; ; i++) {
//                    //同步线程，onNext做耗时操作，2s一次
//                    subscriber.onNext(i);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new SimpleSubscriber<Integer>() {
//                    @Override
//                    public void onNext(Integer var1) {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("result = " + var1);
//                    }
//                });

        //Observable.OnSubscribe
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 10; i++) {
//                    subscriber.onNext(i);
//                }
//            }
//        }).map(new Observable.Transformer<Integer, String>() {
//            @Override
//            public String call(Integer from) {
//                return "maping " + from;
//            }
//        }).subscribe(new SimpleSubscriber<String>() {
//            @Override
//            public void onNext(String var1) {
//                System.out.println(var1);
//            }
//        });
//
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
//                subscriber.onNext(1);
//            }
//        }).subscribeOn(Schedulers.io())
//                .subscribe(new SimpleSubscriber<Integer>() {
//                    @Override
//                    public void onNext(Integer var1) {
//                        System.out.println("Subscriber@ " + Thread.currentThread().getName());
//                        System.out.println(var1);
//                    }
//                });
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
//                subscriber.onNext(1);
//            }
//        })
//                .observeOn(Schedulers.io())
//                .subscribe(new SimpleSubscriber<Integer>() {
//                    @Override
//                    public void onNext(Integer var1) {
//                        System.out.println("Subscriber@ " + Thread.currentThread().getName());
//                        System.out.println(var1);
//                    }
//                });
    }

    private abstract static class SimpleSubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable t) {

        }
    }
}
