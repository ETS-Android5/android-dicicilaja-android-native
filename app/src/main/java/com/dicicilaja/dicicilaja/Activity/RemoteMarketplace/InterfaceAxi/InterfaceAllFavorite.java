package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProduct.AllProduk;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemFavorite.ItemFavorite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface InterfaceAllFavorite {
    @Headers({
            "Accept: application/json",
    })
    @GET("favorite")
    Call<ItemFavorite> getFavorite(@Header("Authorization") String apiKey);
}
