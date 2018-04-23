package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.API.Item.CreateOrder.Area.Area;
import id.variable.dicicilaja.API.Item.CreateOrder.Branch.Branch;
import id.variable.dicicilaja.API.Item.CreateOrder.Colleteral.Colleteral;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fawazrifqi on 22/04/18.
 */

public interface InterfaceAreaBranch {

    @GET("request/colleteral")
    Call<Colleteral> getColleteral();

    @GET("area")
    Call<Area> getArea();

    @GET("branch/{area_id}")
    Call<Branch> getBranch(@Path("area_id") String area_id);
}
