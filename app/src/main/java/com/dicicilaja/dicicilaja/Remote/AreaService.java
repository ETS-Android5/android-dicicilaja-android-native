package com.dicicilaja.dicicilaja.Remote;

import java.util.List;

import com.dicicilaja.dicicilaja.Model.Area;
import com.dicicilaja.dicicilaja.Model.Branch;
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

}
