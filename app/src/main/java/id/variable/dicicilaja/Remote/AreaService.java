package id.variable.dicicilaja.Remote;

import java.util.List;

import id.variable.dicicilaja.Model.Area;
import id.variable.dicicilaja.Item.AreaItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by Rienaldi on 24/12/2017.
 */

public interface AreaService {

    @GET("area")
    Call<List<Area>> getArea();


}
