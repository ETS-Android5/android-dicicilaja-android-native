package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.CabangLainnya.CabangLainnya;
import com.dicicilaja.app.OrderIn.Data.CabangTerdekat.CabangTerdekat;
import com.dicicilaja.app.OrderIn.Data.Kecamatan.Kecamatan;
import com.dicicilaja.app.OrderIn.Data.Kota.Kota;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService2 {

    @Headers({
            "Accept: application/vnd.api+json",
    })

    @GET("area/provinces")
    Call<Provinsi> getProvinsi(@Query("page[size]") int size);

    @GET("area/cities")
    Call<Kota> getKota(@Query("filter[provinsi_id]") int id,
                       @Query("page[size]") int size);

    @GET("area/districts")
    Call<Kecamatan> getKecamatan(@Query("filter[kota_id]") int id,
                                 @Query("page[size]") int size);

    @GET("area/branches")
    Call<CabangRekomendasi> getCabangRekomendasi(@Query("filter[district]") int id,
                                                 @Query("include") String param);

    @GET("area/branches")
    Call<CabangTerdekat> getCabangTerdekat(@Query("filter[city]") int id);

    @GET("area/branches")
    Call<CabangLainnya> getCabangLainnya(@Query("filter[province]") int id);


}
