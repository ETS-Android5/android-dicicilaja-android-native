package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.DatabaseEmployeeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/9/2018.
 */

public interface InterfaceDatabaseEmployee {

    @Headers({
            "Accept: application/json",
    })
    @GET("user/crh")
    Call<DatabaseEmployeeResponse> getDatabaseEmployee(@Header("Authorization") String apiKey);

}