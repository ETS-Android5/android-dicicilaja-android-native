package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.DetailPengajuanResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 1/14/2018.
 */

public interface InterfaceDetailPengajuan {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/{id}")
    Call<DetailPengajuanResponse> getDetailPengajuan(@Header("Authorization") String apiKey, @Path("id") int id);
}
