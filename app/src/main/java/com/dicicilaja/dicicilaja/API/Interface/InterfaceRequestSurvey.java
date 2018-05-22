package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ziterz on 1/30/2018.
 */

public interface InterfaceRequestSurvey {
    @Headers({
            "Accept: application/json",
    })
    @POST("assign")
    @FormUrlEncoded
    Call<ResRequestProcess> assign(@Header("Authorization") String apiKey,
                                   @Field("transaction_id") String transaction_id,
                                   @Field("assigned_id") String assigned_id,
                                   @Field("notes") String notes);
}
