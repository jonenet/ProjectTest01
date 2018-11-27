package com.example.dragger.dialogapp.rx.operator.transform;



import java.util.List;

import rx.Observable;

/**
 * Created by tlh on 2016/8/9.
 */
public class Buffer {
    private static Observable<List<Integer>> bufferObserver() {
        return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(2, 3);
    }


    public static void main(String[] args) {
        bufferObserver().subscribe(System.out::println);
    }
}
