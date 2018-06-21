package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfaceDatabaseCRO {
    @Headers({
            "Accept: application/json",
    })
    @GET("user/cro")
    Call<DatabaseCRO> getDatabaseCRO(@Header("Authorization") String apiKey);
}
