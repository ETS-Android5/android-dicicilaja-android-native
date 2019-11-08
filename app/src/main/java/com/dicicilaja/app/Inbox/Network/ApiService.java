package com.dicicilaja.app.Inbox.Network;

import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/vnd.api+json",
    })

    @GET("/auth/notification/view/personal")
    Call<Provinsi> getNotifPersonal(@Query("user_id") int id);
}
