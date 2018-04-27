package com.example.projecttest01.subjectTest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by ex-zhoulai on 2018/4/12.
 */

public class MoreReactiveList<T> {
    public enum ChangeType {
        ADD, REMOVE
    }

    ;

    final List<T> list = new ArrayList<>();

    public Subject<ChangeType> changes;  // (1)
    public Subject<T> changeValues;
    public SerializedObserver addObserver;                  // (2)
    public Observer<T> removeObserver;

    public MoreReactiveList() {
        changes =
                PublishSubject.<ChangeType>create()
                        .toSerialized();                     // (1)

        changeValues =
                BehaviorSubject.<T>create()
                        .toSerialized();


        addObserver = new SerializedObserver<>(new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(T t) {
                onAdd(t);
            }

            @Override
            public void onError(Throwable e) {
                changes.onError(e);
                changeValues.onError(e);
            }

            @Override
            public void onComplete() {
                changes.onComplete();
                changeValues.onComplete();
            }
        });


        removeObserver = new SerializedObserver<>(new SerializedObserver<>(new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(T t) {
                onRemove(t);
            }

            @Override
            public void onError(Throwable e) {
                changes.onError(e);
                changeValues.onError(e);
            }

            @Override
            public void onComplete() {
                changes.onComplete();
                changeValues.onComplete();
            }
        }));
    }


    public Observable<ChangeType> changes() {
        return changes;
    }

    public Observable<T> changeValues() {
        return changeValues;
    }

    public Observable<List<T>> list() {                   // (3)
        List<T> copy = new ArrayList<>();
        synchronized (list) {
            copy.addAll(list);
        }
        return Observable.fromArray(copy);
    }

    public Observer<T> adder() {                    // (4)
        return addObserver;
    }

    public Observer<T> remover() {
        return removeObserver;
    }


    private void onAdd(T value) {                           // (5)
        synchronized (list) {
            list.add(value);
        }
        changes.onNext(ChangeType.ADD);
        changeValues.onNext(value);
    }

    private void onRemove(T value) {
        synchronized (list) {
            if (!list.remove(value)) {
                return;
            }
        }
        changes.onNext(ChangeType.REMOVE);
        changeValues.onNext(value);
    }

    void clear() {
        synchronized (list) {
            list.clear();
        }
    }

}