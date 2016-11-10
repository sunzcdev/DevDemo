package com.cnjaj.myapplication.rx.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.cnjaj.myapplication.rx.TUtil;

/**
 * Created by Administrator on 2016/11/10.
 */
public class BaseMVPActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity implements BaseView {
    protected M mModel;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        mPresenter.setMV(mModel, this);
    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {

    }

    @Override
    public void requestError(Throwable throwable) {

    }
}
