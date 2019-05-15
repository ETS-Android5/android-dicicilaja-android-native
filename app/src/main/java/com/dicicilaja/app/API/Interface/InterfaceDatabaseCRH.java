package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.DatabaseCRH.DatabaseCRH;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ziterz on 1/9/2018.
 */

public interface InterfaceDatabaseCRH {

    @Headers({
            "Accept: application/json",
    })
    @GET("user/crh")
    Call<DatabaseCRH> getDatabaseCRH(@Header("Authorization") String apiKey,
                                     @Query("page") int page,
                                     @Query("s") String search);

}