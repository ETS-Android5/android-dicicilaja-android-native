package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceUsaha {
    @Headers({
            "Accept: application/json",
    })
    @GET("slider/maxi/usaha")
    Call<AxiSlider> getSlider();
}