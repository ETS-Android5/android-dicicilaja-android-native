package com.dicicilaja.app.NewSimulation.Network;

import com.dicicilaja.app.NewSimulation.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.NewSimulation.Data.HitungSimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.NewSimulation.Data.TahunKendaraan.TahunKendaraan;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // IP : http://103.233.103.23:81/api/
    @Headers({
            "Accept: application/json",
    })

    @GET("tipeobjek/{brand_id}/objekbrand")
    Call<ObjekBrand> getObjekBrand(@Path("brand_id") int id);

    @GET("objekbrand/{id}/objekmodel")
    Call<ObjekModel> getObjekModel(@Path("id") int id,
                                   @Query("area_id") int area_id,
                                   @Query("mrp") boolean mrp);

    @GET("tahunkendaraan/{objek_id}/{area_id}")
    Call<TahunKendaraan> getTahunKendaraan(@Path("objek_id") int objek_id,
                                           @Path("area_id") int area_id);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> hitungCar(@Field("tipe_objek_id") String tipe_objek_id,
                                   @Field("objek_model_id") String objek_model_id,
                                   @Field("tahun_kendaraan") String tahun_kendaraan,
                                   @Field("area_id") String area_id,
                                   @Field("tenor") String tenor,
                                   @Field("tipe_asuransi_id") String tipe_asuransi_id,
                                   @Field("tipe_angsuran_id") String tipe_angsuran_id);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> reHitungCar(@Field("tipe_objek_id") String tipe_objek_id,
                                     @Field("objek_model_id") String objek_model_id,
                                     @Field("tahun_kendaraan") String tahun_kendaraan,
                                     @Field("area_id") String area_id,
                                     @Field("tenor") String tenor,
                                     @Field("tipe_asuransi_id") String tipe_asuransi_id,
                                     @Field("tipe_angsuran_id") String tipe_angsuran_id,
                                     @Field("pencairan") String pencairan);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> reHitungCar2(@Field("tipe_objek_id") String tipe_objek_id,
                                     @Field("objek_model_id") String objek_model_id,
                                     @Field("tahun_kendaraan") String tahun_kendaraan,
                                     @Field("area_id") String area_id,
                                     @Field("tenor") String tenor,
                                     @Field("tipe_asuransi_id") String tipe_asuransi_id,
                                     @Field("tipe_angsuran_id") String tipe_angsuran_id);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> hitungMcy(@Field("tipe_objek_id") String tipe_objek_id,
                                   @Field("objek_model_id") String objek_model_id,
                                   @Field("tahun_kendaraan") String tahun_kendaraan,
                                   @Field("area_id") String area_id,
                                   @Field("tenor") String tenor);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> reHitungMcy(@Field("tipe_objek_id") String tipe_objek_id,
                                     @Field("objek_model_id") String objek_model_id,
                                     @Field("tahun_kendaraan") String tahun_kendaraan,
                                     @Field("area_id") String area_id,
                                     @Field("tenor") String tenor,
                                     @Field("pencairan") String pencairan);

    @POST("simulasi/hitung/")
    @FormUrlEncoded
    Call<HitungSimulasi> reHitungMcy2(@Field("tipe_objek_id") String tipe_objek_id,
                                     @Field("objek_model_id") String objek_model_id,
                                     @Field("tahun_kendaraan") String tahun_kendaraan,
                                     @Field("area_id") String area_id,
                                     @Field("tenor") String tenor);

    @GET("area")
    Call<AreaSimulasi> getAreaSimulasi(@Query("simulasi") Boolean simulasi);

}
