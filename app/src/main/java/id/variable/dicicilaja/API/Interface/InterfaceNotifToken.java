package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ziterz on 2/2/2018.
 */

public interface InterfaceNotifToken {
    @Headers({
            "Accept: application/json",
    })
    @POST("firebase_token")
    @FormUrlEncoded
    Call<ResRequestProcess> assign(@Header("Authorization") String apiKey,
                                   @Field("firebase_token") String firebase_token);
}
