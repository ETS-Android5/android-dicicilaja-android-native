package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.Simulation.Simulation;
import id.variable.dicicilaja.Model.ResObj;
import id.variable.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
