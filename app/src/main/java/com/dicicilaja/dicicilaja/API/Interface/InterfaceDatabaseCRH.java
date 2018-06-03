package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/9/2018.
 */

public interface InterfaceDatabaseCRH {

    @Headers({
            "Accept: application/json",
    })
    @GET("user/crh")
    Call<DatabaseCRH> getDatabaseCRH(@Header("Authorization") String apiKey);

}