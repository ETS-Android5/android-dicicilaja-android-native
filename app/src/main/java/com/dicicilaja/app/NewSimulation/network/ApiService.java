package com.dicicilaja.app.NewSimulation.network;

import com.dicicilaja.app.NewSimulation.data.objekbrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.data.objekmodel.ObjekModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })
    @GET("tipeobjek/{brand_id}/objekbrand")
    Call<ObjekBrand> getObjekBrand(@Path("brand_id") int id);

    @Headers({
            "Accept: application/json",
    })
    @GET("objekbrand/{id}/objekmodel")
    Call<ObjekModel> getObjekModel(@Path("id") int id);
}
