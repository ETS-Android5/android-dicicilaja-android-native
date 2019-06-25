package com.dicicilaja.app.Activity.BusinessReward.network;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.claimReward.ClaimReward;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailSemester.DetailSemester;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailStatus.DetailStatus;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailTestimoni.DetailTestimoni;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.semester.Semester;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.status.Status;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.statusUBClaim.StatusClaim;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.statusUBProduk.StatusProduk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.statusUBTestimoni.StatusTestimoni;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.testimoni.Testimoni;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })

    //GET
    @GET("kategori")
    Call<KategoriProduk> getKategori();

    @GET("product-catalog")
    Call<Produk> getProduk();

    @GET("status")
    Call<Status> getStatus();

    @GET("statusUBProduk")
    Call<StatusProduk> getStatusByProduk();

    @GET("statusUBTestimoni")
    Call<StatusTestimoni> getStatusByTestimoni();

    @GET("statusUBClaim")
    Call<StatusClaim> getStatusByClaim();

    @GET("semester")
    Call<Semester> getSemester();

    @GET("claimReward")
    Call<ClaimReward> getClaimReward();

    @GET("testimoni")
    Call<Testimoni> getTestimoni();

    @GET("product-catalog/{id}")
    Call<DetailProduk> getDetailProduk(@Path("id") int id);

    @GET("status/{id}")
    Call<DetailStatus> getDetailStatus(@Path("id") int id);

    @GET("semester/{id}")
    Call<DetailSemester> getDetailSemester(@Path("id") int id);

    @GET("claimReward/{id}")
    Call<DetailClaimReward> getDetailClaimReward(@Path("id") int id);

    @GET("testimoni/{id}")
    Call<DetailTestimoni> getDetailTestimoni(@Path("id") int id);

    //POST
    @POST("status")
    @FormUrlEncoded
    Call<Status> postStatus(@Field("type") String status_type,
                            @Field("id") String status_id,
                            @Field("nama") String status_nama,
                            @Field("deskripsi") String status_deskripsi,
                            @Field("used_by") String status_used);

    @POST("semester")
    @FormUrlEncoded
    Call<Semester> postSemester(@Field("type") String semester_type,
                                @Field("id") String semester_id,
                                @Field("nama") String semester_nama,
                                @Field("tahun") String semester_tahun);

    @POST("produk")
    @FormUrlEncoded
    Call<Produk> postProduk(@Field("type") String produk_type,
                                @Field("id") String produk_id,
                                @Field("vendor_id") String vendor_id,
                                @Field("point") String produk_point,
                                @Field("nama") String produk_nama,
                                @Field("deskripsi") String produk_deskripsi,
                                @Field("harga") String produk_harga,
                                @Field("ppn") String produk_ppn,
                                @Field("status_id") String produk_status,
                                @Field("foto") String produk_foto,
                                @Field("alt_foto") String produk_alt_foto);

    @POST("claimReward")
    @FormUrlEncoded
    Call<ClaimReward> postClaimReward(@Field("type") String claim_type,
                            @Field("id") String claim_id,
                            @Field("profile_id") String profile_id,
                            @Field("branch_id") String branch_id,
                            @Field("area_id") String area_id,
                            @Field("crh_id") String crh_id,
                            @Field("product_catalog_id") String product_catalog_id,
                            @Field("status_id") String status_id,
                            @Field("alamat") String alamat,
                            @Field("no_resi") String no_resi,
                            @Field("no_po") String no_po, @Field("ongkos_kirim") String ongkir);

    @POST("testimoni")
    @FormUrlEncoded
    Call<Testimoni> postTestimoni(@Field("type") String testimoni_type,
                                  @Field("id") String testimoni_id,
                                  @Field("profile_id") String profile_id,
                                  @Field("claim_reward_id") String claim_id,
                                  @Field("status_id") String status_id,
                                  @Field("testimoni") String testimoni,
                                  @Field("rating") String rating);

    //PUT
    @PUT("status/{id}")
    Call<DetailStatus> putDetailStatus(@Path("id") int id);

    @PUT("semester/{id}")
    Call<DetailSemester> putDetailSemester(@Path("id") int id);

    @PUT("produk/{id}")
    Call<DetailProduk> putDetailProduk(@Path("id") int id);

    @PUT("claimReward/{id}")
    Call<DetailClaimReward> putDetailClaimReward(@Path("id") int id);

    @PUT("testimoni/{id}")
    Call<DetailTestimoni> putDetailTestimoni(@Path("id") int id);

    //DELETE
    @DELETE("status/{id}")
    Call<DetailStatus> deleteDetailStatus(@Path("id") int id);

    @DELETE("semester/{id}")
    Call<DetailSemester> deleteDetailSemester(@Path("id") int id);

    @DELETE("produk/{id}")
    Call<DetailProduk> deleteDetailProduk(@Path("id") int id);

    @DELETE("claimReward/{id}")
    Call<DetailClaimReward> deleteDetailClaimReward(@Path("id") int id);

    @DELETE("testimoni/{id}")
    Call<DetailTestimoni> deleteDetailTestimoni(@Path("id") int id);

//
//    @GET("objekbrand/{id}/objekmodel")
//    Call<ObjekModel> getObjekModel(@Path("id") int id,
//                                   @Query("area_id") int area_id,
//                                   @Query("mrp") boolean mrp);
//
//    @GET("tahunkendaraan/{objek_id}/{area_id}")
//    Call<TahunKendaraan> getTahunKendaraan(@Path("objek_id") int objek_id,
//                                           @Path("area_id") int area_id);
//
//    @POST("simulasi/hitung/")
//    @FormUrlEncoded
//    Call<HitungSimulasi> hitungCar(@Field("tipe_objek_id") String tipe_objek_id,
//                                   @Field("objek_model_id") String objek_model_id,
//                                   @Field("tahun_kendaraan") String tahun_kendaraan,
//                                   @Field("area_id") String area_id,
//                                   @Field("tenor") String tenor,
//                                   @Field("tipe_asuransi_id") String tipe_asuransi_id,
//                                   @Field("tipe_angsuran_id") String tipe_angsuran_id);
//
//    @POST("simulasi/hitung/")
//    @FormUrlEncoded
//    Call<HitungSimulasi> hitungMcy(@Field("tipe_objek_id") String tipe_objek_id,
//                                   @Field("objek_model_id") String objek_model_id,
//                                   @Field("tahun_kendaraan") String tahun_kendaraan,
//                                   @Field("area_id") String area_id,
//                                   @Field("tenor") String tenor);
}
