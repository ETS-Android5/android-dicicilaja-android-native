package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProduct.AllProduk;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceAllProduk {
    @Headers({
            "Accept: application/json",
    })
    @GET("product")
    Call<AllProduk> getProduct();
}
