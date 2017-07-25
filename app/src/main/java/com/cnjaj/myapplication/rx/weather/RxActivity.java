package com.cnjaj.myapplication.rx.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.cnjaj.myapplication.R;
import com.cnjaj.myapplication.rx.base.BaseMVPActivity;
import com.cnjaj.myapplication.rx.entity.DailyWeather;

import java.util.List;

/**
 * Rx应用例子
 * 1、获取当前的ip地址
 * http://ip.taobao.com/service/getIpInfo2.php?ip=myip
 * 2、获取当前的城市
 * 3、获取当前城市的天气
 * https://api.thinkpage.cn/v3/weather/daily.json?
 * 4、根据当前温度状况显示出不同的背景图片，及提示信息
 * 2.1、获取当前省份，获取当前省份所有的城市，分别查询所有城市的天气信息，根据温度显示图片，提示
 */
public class RxActivity extends BaseMVPActivity<Presenter, Model> implements WeatherContract.WeatherView {

    private static final String TAG = "RxActivity";
    private ListView mWeatherLv;
    private ArrayAdapter<Object> mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        mWeatherLv = (ListView) findViewById(R.id.weather_list);
        mWeatherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mWeatherLv.setAdapter(mWeatherAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.current_weather:
                mPresenter.getTodayWeather();
                return true;
            case R.id.futher_weather:
                mPresenter.getFutureWeather(10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void requestStart() {
        Log.i(TAG, "请求开始");
    }

    @Override
    public void requestEnd() {
        Log.i(TAG, "请求结束");

    }

    @Override
    public void requestError(Throwable throwable) {
        Log.i(TAG, "请求失败" + throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    public void showDailyWeather(DailyWeather dailyWeather) {
        DailyWeather.ResultsBean resultsBean = dailyWeather.getResults().get(0);
        List<DailyWeather.ResultsBean.DailyBean> daily = resultsBean.getDaily();
        if (daily != null && !daily.isEmpty()) {
            setTitle(resultsBean.getLocation().getName() + "未来十天天气");
            mWeatherAdapter.clear();
            mWeatherAdapter.addAll(daily);
        }
    }

    @Override
    public void showNowWeather(NowWeather nowWeather) {
        NowWeather.ResultsBean result = nowWeather.getResults().get(0);
        setTitle(result.getLocation().getName() + "今天24小时天气");
        mWeatherAdapter.clear();
        mWeatherAdapter.add(result.getNow().toString());
    }
}
