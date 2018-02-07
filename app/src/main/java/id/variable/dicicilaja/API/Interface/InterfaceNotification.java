package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Notification.Notification;
import id.variable.dicicilaja.API.Item.Request.Request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by ziterz on 2/8/2018.
 */

public interface InterfaceNotification {
    @Headers({
            "Accept: application/json",
    })
    @GET("notification")
    Call<Notification> getNotification(@Header("Authorization") String apiKey);
}
