package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.Request.Request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfaceRequest {
    @Headers({
            "Accept: application/json",
    })
    @GET("request")
    Call<Request> getRequest(@Header("Authorization") String apiKey);
}
