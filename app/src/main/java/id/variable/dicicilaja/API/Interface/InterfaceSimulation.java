package id.variable.dicicilaja.API.Interface;

import java.util.List;

import id.variable.dicicilaja.API.Item.AreaRequest.AreaRequest;
import id.variable.dicicilaja.API.Item.Colleteral.Colleteral;
import id.variable.dicicilaja.API.Item.Colleteral.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Model.Area;
import id.variable.dicicilaja.Model.Branch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 14/04/18.
 */

public interface InterfaceSimulation {

    @GET("simulation/area_request")
    Call<AreaRequest> getAreaRequest();

    @GET("simulation/colleteral")
    Call<Colleteral> getColleteral();
}
