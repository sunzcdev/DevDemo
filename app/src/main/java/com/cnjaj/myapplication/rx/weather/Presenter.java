package com.cnjaj.myapplication.rx.weather;

import android.util.Log;
import com.cnjaj.myapplication.rx.entity.Position;
import com.cnjaj.myapplication.rx.entity.Weather;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Presenter extends WeatherContract.WeatherPresenter {
    @Override
    public void getFutureWeather(final int days) {
        Subscription weatherSubscrition = mModel.getCurrentPosition()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.requestStart();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Position, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> call(Position position) {
                        Log.i(TAG, "获取到当前的位置信息:" + position.toString());
                        return mModel.getFutureWeather(position.getData().getCity(), days);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Weather>() {
                    @Override
                    public void call(Weather weathers) {
                        mView.showWeather(weathers);
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
        Subscription weatherSubscrition = mModel.getCurrentPosition()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.requestStart();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Position, Observable<Weather>>() {
                    @Override
                    public Observable<Weather> call(Position position) {
                        return mModel.getTodayWeather(position.getData().getCity());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Weather>() {
                    @Override
                    public void call(Weather weather) {
                        mView.showWeather(weather);
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
