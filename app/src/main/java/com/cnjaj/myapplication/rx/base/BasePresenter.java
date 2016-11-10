package com.cnjaj.myapplication.rx.base;

import com.cnjaj.myapplication.rx.rx.RxManager;

/**
 * Created by Administrator on 2016/11/10.
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected RxManager mRxManager = new RxManager();
    protected V mView;
    protected M mModel;

    public void setMV(M m, V v) {
        this.mModel = m;
        this.mView = v;
    }

    public void onDestory() {
        mRxManager.clear();
    }
}
