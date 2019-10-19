package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.CabangLainnya.CabangLainnya;
import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.CabangRekomendasi;
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
    Call<Kota> getKota(@Query("filter[provinsi_id]") String id,
                       @Query("page[size]") int size);


    @GET("area/branches")
    Call<CabangRekomendasi> getCabangRekomendasi(@Query("filter[district]") String id,
                                                 @Query("include") String param,
                                                 @Query("page[size]") int size);

    @GET("area/branches")
    Call<CabangTerdekat> getCabangTerdekat(@Query("filter[city]") String id,
                                           @Query("include") String param,
                                           @Query("page[size]") int size);

    @GET("area/branches")
    Call<CabangLainnya> getCabangLainnya(@Query("filter[province]") String id,
                                         @Query("include") String param,
                                         @Query("page[size]") int size);

    @GET("area/districts")
    Call<Kecamatan> getKecamatan(@Query("filter[kota_id]") String id,
                                 @Query("page[size]") int size);


}
