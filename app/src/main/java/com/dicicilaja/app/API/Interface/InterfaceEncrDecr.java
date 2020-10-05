package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.Colleteral.Colleteral;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface InterfaceEncrDecr {

    @GET("v2/rgstrnsvc/encr/{id}")
    Call<String> getEncr(@Path("id") String id);

    @GET("v2/rgstrnsvc/decr")
    Call<String> getDecr(@Query("kode") String kode);
}
