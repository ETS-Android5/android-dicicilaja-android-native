package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemNotificationAxi.NotificationAxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;
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
