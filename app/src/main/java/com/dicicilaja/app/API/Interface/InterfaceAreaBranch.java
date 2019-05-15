package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.CreateOrder.Area.Area;
import com.dicicilaja.app.API.Model.CreateOrder.Branch.Branch;
import com.dicicilaja.app.API.Model.CreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 22/04/18.
 */

public interface InterfaceAreaBranch {

    @GET("coleteral")
    Call<Colleteral> getColleteral();

    @GET("area")
    Call<Area> getArea();

    @GET("branch/{area_id}")
    Call<Branch> getBranch(@Path("area_id") String area_id);
}
