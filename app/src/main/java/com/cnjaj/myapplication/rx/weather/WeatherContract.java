package com.cnjaj.myapplication.rx.weather;

import com.cnjaj.myapplication.rx.base.BaseModel;
import com.cnjaj.myapplication.rx.base.BasePresenter;
import com.cnjaj.myapplication.rx.base.BaseView;
import com.cnjaj.myapplication.rx.entity.Position;
import com.cnjaj.myapplication.rx.entity.Weather;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class WeatherContract {
    public interface WeatherModel extends BaseModel {
        Observable<Weather> getFutureWeather(String position, int days);

        Observable<Weather> getTodayWeather(String position);

        Observable<Position> getCurrentPosition();
    }

    public interface WeatherView extends BaseView {
        void showWeather(Weather weather);
    }

    public static abstract class WeatherPresenter extends BasePresenter<WeatherModel, WeatherView> {
        public abstract void getFutureWeather(int days);

        public abstract void getTodayWeather();
    }
}
