package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.InfoJaringan;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceInfoJaringan {
    @Headers({
            "Accept: application/json",
    })
    @GET("axi/infojaringan")
    Call<InfoJaringan> getInfoJaringan(@Header("Authorization") String apiKey);
}
