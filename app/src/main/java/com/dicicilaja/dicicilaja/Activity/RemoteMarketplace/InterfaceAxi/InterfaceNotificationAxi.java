package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemNotificationAxi.NotificationAxi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceNotificationAxi {
    @Headers({
            "Accept: application/json",
    })
    @GET("jodi/notificationaxi")
    Call<NotificationAxi> getNotif(@Header("Authorization") String apiKey);
}
