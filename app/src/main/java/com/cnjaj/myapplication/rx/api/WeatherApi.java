package com.cnjaj.myapplication.rx.api;

import com.cnjaj.myapplication.rx.entity.DailyWeather;
import com.cnjaj.myapplication.rx.weather.NowWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface WeatherApi {
    @GET("weather/daily.json?key=wos8thz2mhge7xdt&language=zh-Hans&unit=c&start=0")
    Observable<DailyWeather> getDailyWeather(@Query("location") String location, @Query("days") int days);

    @GET("weather/now.json?key=wos8thz2mhge7xdt&language=zh-Hans&unit=c&start=0")
    Observable<NowWeather> getNowWeather(@Query("location") String location, @Query("hours") int hours);
}
