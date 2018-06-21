package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ziterz on 2/1/2018.
 */

public interface InterfaceKeputusanPinjaman {
    @Headers({
            "Accept: application/json",
    })
    @POST("assign")
    @FormUrlEncoded
    Call<ResRequestProcess> assign(@Header("Authorization") String apiKey,
                                   @Field("transaction_id") String transaction_id,
                                   @Field("assigned_id") String assigned_id,
                                   @Field("notes") String notes,
                                   @Field("decision") String decision,
                                   @Field("pk_number") String pk_number,
                                   @Field("amount") String amount);
}
