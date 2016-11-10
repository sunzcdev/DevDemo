package com.cnjaj.myapplication.rx.api;

import com.cnjaj.myapplication.rx.entity.Position;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface PositionApi {
    @GET("getIpInfo2.php?ip=myip")
    Observable<Position> getMyPosition();
}
