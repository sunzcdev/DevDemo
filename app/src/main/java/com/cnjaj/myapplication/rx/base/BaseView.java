package com.cnjaj.myapplication.rx.base;

/**
 * Created by Administrator on 2016/11/10.
 */
public interface BaseView {
    void requestStart();

    void requestEnd();

    void requestError(Throwable throwable);
}
