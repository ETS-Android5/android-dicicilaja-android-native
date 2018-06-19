package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceSehat {
    @Headers({
            "Accept: application/json",
    })
    @GET("slider/maxi/sehat")
    Call<AxiSlider> getSlider();

}
