package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.BranchOffice.Data.AreaBranchOffice.AreaBranchOffice;
import com.dicicilaja.app.BranchOffice.Data.BranchOffice.BranchOffice;
import com.dicicilaja.app.BranchOffice.Data.KotaBranchOffice.KotaBranchOffice;
import com.dicicilaja.app.OrderIn.Data.Area;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })

    @GET("area/provinsi")
    Call<Area> getArea();

}
