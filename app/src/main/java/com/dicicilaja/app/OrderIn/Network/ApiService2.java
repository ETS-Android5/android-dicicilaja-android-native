package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.CabangLainnya.CabangLainnya;
import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.CabangRekomendasi;
import com.dicicilaja.app.OrderIn.Data.CabangTerdekat.CabangTerdekat;
import com.dicicilaja.app.OrderIn.Data.Kecamatan.Kecamatan;
import com.dicicilaja.app.OrderIn.Data.Kelurahan.Kelurahan;
import com.dicicilaja.app.OrderIn.Data.Kota.Kota;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService2 {

    @Headers({
            "Accept: application/vnd.api+json",
    })

    @GET("v3/area/provinces")
    Call<Provinsi> getProvinsi(@Header("Authorization") String apiKey,
                               @Query("page[size]") int size);

    @GET("v3/area/cities")
    Call<Kota> getKota(@Header("Authorization") String apiKey,
                       @Query("filter[provinsi_id]") String id,
                       @Query("page[size]") int size);


    @GET("v3/area/branches")
    Call<CabangRekomendasi> getCabangRekomendasi(@Header("Authorization") String apiKey,
                                                 @Query("filter[district]") String id,
                                                 @Query("include") String param,
                                                 @Query("page[size]") int size);

    @GET("v3/area/branches")
    Call<CabangTerdekat> getCabangTerdekat(@Header("Authorization") String apiKey,
                                           @Query("filter[city]") String id,
                                           @Query("include") String param,
                                           @Query("page[size]") int size);

    @GET("v3/area/branches")
    Call<CabangLainnya> getCabangLainnya(@Header("Authorization") String apiKey,
                                         @Query("filter[province]") String id,
                                         @Query("include") String param,
                                         @Query("page[size]") int size);

    @GET("v3/area/districts")
    Call<Kecamatan> getKecamatan(@Header("Authorization") String apiKey,
                                 @Query("filter[kota_id]") String id,
                                 @Query("page[size]") int size);

    @GET("v3/area/villages")
    Call<Kelurahan> getKelurahan(@Header("Authorization") String apiKey,
                                 @Query("filter[distrik_id]") String id,
                                 @Query("page[size]") int size);
}
