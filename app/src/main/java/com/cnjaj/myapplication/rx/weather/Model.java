package com.cnjaj.myapplication.rx.weather;

import com.cnjaj.myapplication.rx.api.ApiManager;
import com.cnjaj.myapplication.rx.entity.DailyWeather;
import com.cnjaj.myapplication.rx.entity.Position;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class Model implements WeatherContract.WeatherModel {
    @Override
    public Observable<DailyWeather> getDailyWeather(String position, int days) {
        return ApiManager.getInstance().createWeatherApi().getDailyWeather(position, days);
    }

    @Override
    public Observable<NowWeather> getNowWeather(String position) {
        return ApiManager.getInstance().createWeatherApi().getNowWeather(position, 24);
    }

    @Override
    public Observable<Position> getCurrentPosition() {
        return ApiManager.getInstance().createPositionApi().getMyPosition("myip");
    }
}
