package id.variable.dicicilaja.Remote;

import java.util.List;

import id.variable.dicicilaja.API.Item.AreaRequest.Datum;
import id.variable.dicicilaja.Model.Area;
import id.variable.dicicilaja.Model.Branch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rienaldi on 24/12/2017.
 */

public interface AreaService {

    @GET("area")
    Call<List<Area>> getArea();

    @GET("branch/{area_id}")
    Call<List<Branch>> getBranch(@Path("area_id") String area_id);

    @GET("simulation/area_request")
    Call<List<Datum>> getAreaRequest();

    @GET("simulation/colleteral")
    Call<List<id.variable.dicicilaja.API.Item.Colleteral.Datum>> getColleteral();

}
