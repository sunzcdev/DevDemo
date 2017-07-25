package com.cnjaj.myapplication.rx.api;

import com.cnjaj.myapplication.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ApiManager {
    private static ApiManager manager = new ApiManager();

    public static ApiManager getInstance() {
        return manager;
    }

    public WeatherApi createWeatherApi() {
        return getApi(BuildConfig.WEATHER_API_URL, WeatherApi.class);
    }

    public PositionApi createPositionApi() {
        return getApi("http://ip.taobao.com/service/", PositionApi.class);
    }

    private <T> T getApi(String baseUrl, Class<T> tClass) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(tClass);
    }
}
