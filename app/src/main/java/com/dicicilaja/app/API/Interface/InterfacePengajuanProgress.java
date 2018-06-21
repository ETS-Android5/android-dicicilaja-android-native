package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Item.PengajuanProgressResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 1/25/2018.
 */

public interface InterfacePengajuanProgress {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/progress")
    Call<PengajuanProgressResponse> getPengajuanProgress(@Header("Authorization") String apiKey);
}
