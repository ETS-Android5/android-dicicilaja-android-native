package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.Request.Request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfaceRequest {
    @Headers({
            "Accept: application/json",
    })
    @GET("request?page=1")
    Call<Request> getRequest(@Header("Authorization") String apiKey);

    @Headers({
            "Accept: application/json",
    })
    @GET("request")
    Call<Request> getRequest(@Header("Authorization") String apiKey,
                             @Query("page") int page);

}
