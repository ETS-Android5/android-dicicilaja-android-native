package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;


import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public interface InterfaceAreaBranch {
    @GET("request/colleteral")
    Call<Colleteral> getColleteral();

    @GET("area")
    Call<Area> getArea();

    @GET("branch/{area_id}")
    Call<Branch> getBranch(@Path("area_id") String area_id);
}
