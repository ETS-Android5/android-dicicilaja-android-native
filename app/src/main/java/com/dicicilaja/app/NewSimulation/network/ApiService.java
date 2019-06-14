package com.dicicilaja.app.NewSimulation.network;

import com.dicicilaja.app.NewSimulation.data.areasimulasi.AreaSimulasi;
import com.dicicilaja.app.NewSimulation.data.hitungsimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.data.objekbrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.data.objekmodel.ObjekModel;
import com.dicicilaja.app.NewSimulation.data.tahunkendaraan.TahunKendaraan;
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
    Call<ObjekModel> getObjekModel(@Path("id") int id);

    @GET("tahunkendaraan/{objek_id}/{area_id}")
    Call<TahunKendaraan> getTahunKendaraan(@Path("objek_id") int objek_id, @Path("area_id") int area_id);

    @GET("area?simulasi=true")
    Call<AreaSimulasi> getAreaSimulasi();

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
    Call<HitungSimulasi> hitungMcy(@Field("tipe_objek_id") String tipe_objek_id,
                                @Field("objek_model_id") String objek_model_id,
                                @Field("tahun_kendaraan") String tahun_kendaraan,
                                @Field("area_id") String area_id,
                                @Field("tenor") String tenor);



}
