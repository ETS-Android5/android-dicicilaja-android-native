package com.dicicilaja.app.BranchOffice.Network;

import com.dicicilaja.app.BranchOffice.Data.AreaBranchOffice.AreaBranchOffice;
import com.dicicilaja.app.BranchOffice.Data.BranchOffice.BranchOffice;
import com.dicicilaja.app.BranchOffice.Data.KotaBranchOffice.KotaBranchOffice;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })

    /*
    Area pada Branch Office
    */
    @GET("area")
    Call<AreaBranchOffice> getArea();

    /*
    Kota pada Branch Office
    */
    @GET("kota")
    Call<KotaBranchOffice> getKota(@Query("region") String region);

    /*
    Kantor Cabang pada Branch Office
    */
    @GET("cabang")
    Call<BranchOffice> getBranch(@Query("kota") String kota);

}
