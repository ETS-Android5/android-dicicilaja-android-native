package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.DetailPengajuanStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by ziterz on 1/15/2018.
 */

public interface InterfaceDetailPengajuanStatus {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/{id}/status")
    Call<DetailPengajuanStatus> getDetailPengajuanStatus(@Header("Authorization") String apiKey, @Path("id") int id);
}
