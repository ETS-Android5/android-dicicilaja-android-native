package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProduct.AllProduk;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemFavorite.ItemFavorite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface InterfaceAllFavorite {
    @Headers({
            "Accept: application/json",
    })
    @GET("favorite/{id}")
    Call<ItemFavorite> getFavorite(@Header("Authorization") String apiKey,
                                   @Path("id") String id);
}
