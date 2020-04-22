package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider.Slider;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface InterfaceCustomerSlider {
    @GET("content/images")
    Call<Slider> getSlider(@Header("Authorization") String apiKey,
                           @Query("filter[page_id]") String page_id,
                           @Query("filter[type]") String type);
}
