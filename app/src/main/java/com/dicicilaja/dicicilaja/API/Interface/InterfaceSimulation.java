package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.AreaRequest.AreaRequest;
import com.dicicilaja.dicicilaja.API.Item.Colleteral.Colleteral;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;

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
