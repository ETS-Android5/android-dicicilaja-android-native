package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.PengajuanAxi.PengajuanAxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public interface InterfacePengajuanAxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/user")
    Call<PengajuanAxi> getPengajuanAxi(@Header("Authorization") String apiKey,
                                       @Query("page") int page);

}
