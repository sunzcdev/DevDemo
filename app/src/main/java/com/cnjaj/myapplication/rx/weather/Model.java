package com.cnjaj.myapplication.rx.weather;

import com.cnjaj.myapplication.rx.api.ApiManager;
import com.cnjaj.myapplication.rx.entity.Position;
import com.cnjaj.myapplication.rx.entity.Weather;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Model implements WeatherContract.WeatherModel {
    @Override
    public Observable<Weather> getFutureWeather(String position, int days) {
        return ApiManager.getInstance().createWeatherApi().getFutureWeather(position, days);
    }

    @Override
    public Observable<Weather> getTodayWeather(String position) {
        return ApiManager.getInstance().createWeatherApi().getCurrentDayWeather(position, 24);
    }

    @Override
    public Observable<Position> getCurrentPosition() {
        return ApiManager.getInstance().createPositionApi().getMyPosition();
    }
}
