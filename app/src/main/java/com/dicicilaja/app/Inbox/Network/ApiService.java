package com.dicicilaja.app.Inbox.Network;

import com.dicicilaja.app.Inbox.Data.Popup.Popup;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/vnd.api+json",
    })

    @GET("auth/notification/view/personal")
    Call<Provinsi> getNotifPersonal(@Query("user_id") int id);

    @GET("auth/popup/view")
    Call<Popup> getPopup(@Query("role") String role);
}
