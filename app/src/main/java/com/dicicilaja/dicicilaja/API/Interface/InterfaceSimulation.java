package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.AreaRequest.AreaRequest;
import com.dicicilaja.dicicilaja.API.Item.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fawazrifqi on 14/04/18.
 */

public interface InterfaceSimulation {

    @GET("simulation/area_request")
    Call<AreaRequest> getAreaRequest();

    @GET("simulation/colleteral")
    Call<Colleteral> getColleteral();
}
