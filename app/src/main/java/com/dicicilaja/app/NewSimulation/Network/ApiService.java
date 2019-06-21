package com.dicicilaja.app.NewSimulation.Network;

import com.dicicilaja.app.NewSimulation.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.NewSimulation.Data.HitungSimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.NewSimulation.Data.TahunKendaraan.TahunKendaraan;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })

    /*
    Merk kendaraan berdasarkan type_id kendaraan
    1 = Mobil
    2 = Motor

    example :
    tipeobjek/1/objekbrand
    */
    @GET("tipeobjek/{type_id}/objekbrand")
    Call<ObjekBrand> getObjekBrand(@Path("type_id") int type_id);

    /*
    Model kendaraan berdasarkan objek_id kendaraan

    Sample Data (objek_id) :
    1 = AUDI (Mobil)
    45 = HONDA (Motor)

    example :
    objekbrand/1/objekmodel
    */
    @GET("objekbrand/{objek_id}/objekmodel")
    Call<ObjekModel> getObjekModel(@Path("objek_id") int objek_id,
                                   @Query("area_id") int area_id,
                                   @Query("mrp") boolean mrp);

    /*
    Area pada New Simulation
    */
    @GET("area")
    Call<AreaSimulasi> getAreaSimulasi(@Query("simulasi") boolean simulasi);

    /*
    Tahun kendaraan berdasarkan model_id kendaraan dan area_id

    Sample Data (model_id):
    13 = 320 I AT (BMW)

    Sample Data (area_id):
    9 = Jabodetabekser

    example :
    tahunkendaraan/13/9
    */
    @GET("tahunkendaraan/{model_id}/{area_id}")
    Call<TahunKendaraan> getTahunKendaraan(@Path("model_id") int model_id,
                                           @Path("area_id") int area_id);

    /*
    Menghitung simulasi Mobil
    */
    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> hitungCar(@Field("tipe_objek_id") String tipe_objek_id,
                                @Field("objek_model_id") String objek_model_id,
                                @Field("tahun_kendaraan") String tahun_kendaraan,
                                @Field("area_id") String area_id,
                                @Field("tenor") String tenor,
                                @Field("tipe_asuransi_id") String tipe_asuransi_id,
                                @Field("tipe_angsuran_id") String tipe_angsuran_id);

    /*
    Menghitung simulasi Motor
    */
    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> hitungMcy(@Field("tipe_objek_id") String tipe_objek_id,
                                @Field("objek_model_id") String objek_model_id,
                                @Field("tahun_kendaraan") String tahun_kendaraan,
                                @Field("area_id") String area_id,
                                @Field("tenor") String tenor);
}
