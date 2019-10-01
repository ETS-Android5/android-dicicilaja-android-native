package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.OrderIn.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.OrderIn.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.OrderIn.Data.TahunKendaraan.TahunKendaraan;
import com.dicicilaja.app.OrderIn.Data.TipeObjek.TipeObjek;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "Accept: application/json",
    })

    @GET("tipeobjek/{brand_id}/objekbrand")
    Call<ObjekBrand> getObjekBrand(@Path("brand_id") int id);

    @GET("tipeobjek/")
    Call<TipeObjek> getTipeObjek();

    @GET("objekbrand/{id}/objekmodel")
    Call<ObjekModel> getObjekModel(@Path("id") int id,
                                   @Query("area_id") int area_id,
                                   @Query("mrp") boolean mrp);

    @GET("tahunkendaraan/{objek_id}/{area_id}")
    Call<TahunKendaraan> getTahunKendaraan(@Path("objek_id") int objek_id,
                                           @Path("area_id") int area_id);

    @GET("area")
    Call<AreaSimulasi> getAreaSimulasi(@Query("simulasi") Boolean simulasi);

}
