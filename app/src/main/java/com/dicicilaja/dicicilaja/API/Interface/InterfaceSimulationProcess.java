package com.dicicilaja.dicicilaja.API.Interface;

import com.dicicilaja.dicicilaja.API.Item.Simulation.Simulation;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 17/04/18.
 */

public interface InterfaceSimulationProcess {

    @POST("jodi/simulation")
    @FormUrlEncoded
    Call<Simulation> assign(@Field("area") String area,
                            @Field("colleteral") String colleteral,
                            @Field("ammount") String ammount,
                            @Field("tenor") String tenor);
}
