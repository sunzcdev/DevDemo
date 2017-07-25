package com.cnjaj.myapplication.rx.api;

import com.cnjaj.myapplication.rx.entity.Position;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface PositionApi {
    @FormUrlEncoded()
    @POST("getIpInfo2.php")
    Observable<Position> getMyPosition(@Field("ip") String ip);
}
