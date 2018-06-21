package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.Recommend.Recommend;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 06/04/18.
 */

public interface InterfaceRecommend {
    @Headers({
            "Accept: application/json",
    })
    @GET("/product/recommend")
    Call<Recommend> getRecommend();
}
