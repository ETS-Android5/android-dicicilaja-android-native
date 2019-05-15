package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.Colleteral.Colleteral;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 14/04/18.
 */

public interface InterfaceSimulation {

    @GET("coleteral")
    Call<Colleteral> getColleteral();

    @GET("area")
    Call<Area> getArea();

    @GET("branch/{area_id}")
    Call<Branch> getBranch(@Path("area_id") String area_id);
}
