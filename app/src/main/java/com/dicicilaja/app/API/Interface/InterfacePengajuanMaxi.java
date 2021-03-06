package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.PengajuanMaxi.PengajuanMaxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfacePengajuanMaxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("request/user")
    Call<PengajuanMaxi> getPengajuanMaxi(@Header("Authorization") String apiKey,
                                         @Query("page") int page);
}
