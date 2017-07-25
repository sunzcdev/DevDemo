package com.cnjaj.myapplication.rx.weather;

import com.cnjaj.myapplication.rx.entity.DailyWeather;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Presenter extends WeatherContract.WeatherPresenter {
    @Override
    public void getFutureWeather(final int days) {
        Subscription weatherSubscrition = mModel.getDailyWeather("ip", days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DailyWeather>() {
                    @Override
                    public void call(DailyWeather weathers) {
                        mView.showDailyWeather(weathers);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.requestError(throwable);
                        mView.requestEnd();
                    }
                });
        mRxManager.add(weatherSubscrition);
    }

    @Override
    public void getTodayWeather() {
        Subscription weatherSubscrition = mModel.getNowWeather("ip")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NowWeather>() {
                    @Override
                    public void call(NowWeather weather) {
                        mView.showNowWeather(weather);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.requestError(throwable);
                        mView.requestEnd();
                    }
                });
        mRxManager.add(weatherSubscrition);
    }

}
