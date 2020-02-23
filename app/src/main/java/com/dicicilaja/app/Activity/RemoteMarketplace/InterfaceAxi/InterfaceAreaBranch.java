package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;


import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public interface InterfaceAreaBranch {
    @GET("coleteral")
    Call<Colleteral> getColleteral();

    @GET("area/areas?filter[transaksi]=true&page[size]=100")
    Call<Area> getArea();

    @GET("area/branches?page[size]=100")
    Call<Branch> getBranch(@Query("filter[area_id]") String area_id);
}
