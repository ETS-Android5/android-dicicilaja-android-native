package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.Promo.Promo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 05/04/18.
 */

public interface InterfacePromo {
    @Headers({
            "Accept: application/json",
    })
    @GET("/product/promo")
    Call<Promo> getPromo();
}
