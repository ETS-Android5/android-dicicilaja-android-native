package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider.Slider;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceEdukasi {
    @Headers({
            "Accept: application/json",
    })
    @GET("slider/maxi/edukasi")
    Call<Slider> getSlider();
}
