package com.example.dragger.dialogapp.rx.operator.filter;


/**
 * Created by tlh on 2016/8/9.
 */
public class Filter$ElementAt {
    public static void main(String[] args) {
        Observable.just(0, 1, 2, 3, 4, 5).elementAt(2).subscribe(System.out::println);
        Observable.just(0, 1, 2, 3, 4, 5).filter(integer -> integer > 3)
                .subscribe(System.out::println);
    }
}
