package com.dicicilaja.dicicilaja.API.Interface;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ziterz on 2/7/2018.
 */

public interface InterfaceLogout {
    @Headers({
            "Accept: application/json",
    })
    @POST("logout")
    @FormUrlEncoded
    Call logout(@Header("Authorization") String apiKey);
}

