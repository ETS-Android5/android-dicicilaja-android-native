package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.Model.Logout;

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
    Call<Logout> logout(@Header("Authorization") String apiKey);
}

