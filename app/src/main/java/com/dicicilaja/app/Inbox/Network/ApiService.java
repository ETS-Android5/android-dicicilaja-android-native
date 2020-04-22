package com.dicicilaja.app.Inbox.Network;

import com.dicicilaja.app.Inbox.Data.Notif.Notif;
import com.dicicilaja.app.Inbox.Data.Popup.Popup;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/vnd.api+json",
    })

    @GET("auth/notification/view/personal")
    Call<Notif> getNotifPersonal(@Header("Authorization") String apiKey,
                                 @Query("user_id") String id);

    @GET("auth/popups/view")
    Call<Popup> getPopup(@Header("Authorization") String apiKey,
                         @Query("role") String role);
}
