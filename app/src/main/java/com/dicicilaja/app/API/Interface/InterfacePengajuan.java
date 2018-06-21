package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.PengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 30/12/2017.
 */

public interface InterfacePengajuan {

    @Headers({
            "Accept: application/json",
    })
    @GET("request")
    Call<PengajuanResponse> getPengajuan(@Header("Authorization") String apiKey);

}
