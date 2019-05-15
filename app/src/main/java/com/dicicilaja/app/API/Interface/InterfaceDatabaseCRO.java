package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.DatabaseCRO.DatabaseCRO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfaceDatabaseCRO {
    @Headers({
            "Accept: application/json",
    })
    @GET("user/cro")
    Call<DatabaseCRO> getDatabaseCRO(@Header("Authorization") String apiKey,
                                     @Query("page") int page,
                                     @Query("s") String search);
}
