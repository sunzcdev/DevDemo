package com.cnjaj.myapplication.rx.api;

import com.cnjaj.myapplication.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ApiManager {
    private static ApiManager manager = new ApiManager();

    public static ApiManager getInstance() {
        return manager;
    }

    public WeatherApi createWeatherApi() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
    }

    public PositionApi createPositionApi() {
        return new Retrofit.Builder()
                .baseUrl("http://ip.taobao.com/service/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(PositionApi.class);
    }
}
