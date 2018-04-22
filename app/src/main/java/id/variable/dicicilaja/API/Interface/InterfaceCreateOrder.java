package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.CreateOrder.Area.Area;
import id.variable.dicicilaja.API.Item.CreateOrder.Branch.Branch;
import id.variable.dicicilaja.API.Item.CreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 21/04/18.
 */

public interface InterfaceCreateOrder {

    @GET("request/colleteral")
    Call<Colleteral> getColleteral();


}
