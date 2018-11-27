package com.example.rx.frame;

public class OperatorFilter<T> implements Observable.Operator<T, T> {
    private IFun<? super T, Boolean> fun;

    public OperatorFilter(IFun<? super T, Boolean> fun) {
        this.fun = fun;
    }

    @Override
    public Observer<? super T> call(final Observer<? super T> observer) {
        return new Observer<T>() {
            @Override
            public void update(T t) {
                if (fun.call(t)) {
                    observer.update(t);
                }

            }
        };
    }
}
