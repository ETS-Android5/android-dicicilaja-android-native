package com.dicicilaja.app.BusinessReward.network;

import android.graphics.Point;

import com.dicicilaja.app.BusinessReward.dataAPI.area2.Area2;
import com.dicicilaja.app.BusinessReward.dataAPI.branch.Branch;
import com.dicicilaja.app.BusinessReward.dataAPI.cabang.Cabang;
import com.dicicilaja.app.BusinessReward.dataAPI.claimReward.ClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.claimReward.PostClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.detailProfile.DetailProfile;
import com.dicicilaja.app.BusinessReward.dataAPI.detailSemester.DetailSemester;
import com.dicicilaja.app.BusinessReward.dataAPI.detailStatus.DetailStatus;
import com.dicicilaja.app.BusinessReward.dataAPI.detailTestimoni.DetailTestimoni;
import com.dicicilaja.app.BusinessReward.dataAPI.foto.Foto;
import com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.ClaimRewards;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.point.ExistingPoint;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.BusinessReward.dataAPI.rewardphase.RewardPhase;
import com.dicicilaja.app.BusinessReward.dataAPI.semester.Semester;
import com.dicicilaja.app.BusinessReward.dataAPI.status.Status;
import com.dicicilaja.app.BusinessReward.dataAPI.statusUBClaim.StatusClaim;
import com.dicicilaja.app.BusinessReward.dataAPI.statusUBProduk.StatusProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.statusUBTestimoni.StatusTestimoni;
import com.dicicilaja.app.BusinessReward.dataAPI.testimoni.Testimoni;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/json",
    })

    @GET("existing/point")
    Call<ExistingPoint> getExistingPoint(@Header("Authorization") String apiKey,
                                         @Query("profile_id") String profileId);

    @GET("reward-phase/1")
    Call<RewardPhase> getRewardPhase(@Header("Authorization") String apiKey);

    //GET
    @GET("existing/kategori")
    Call<KategoriProduk> getKategori(@Header("Authorization") String apiKey);

    @GET("branch")
    Call<Branch> getCabang(@Path("id") int cabang_id);

    @GET("area/cabang/get-by-area/{id}")
    Call<Cabang> getAllCabang(@Path("id") int cabang_id);

    @GET("area/areas?filter[transaksi]=true")
    Call<Area2> getArea();

    @GET("existing/axi-foto")
    Call<FotoKtpNpwp> getFoto(@Header("Authorization") String apiKey,
                              @Query("axi_id") String axi_id);

    @GET("claim-reward")
    Call<ClaimRewards> getClaim(@Query("profile_id") String profile_id,
                                @Query("page") int page,
                                @Query("ob") String ob,
                                @Query("ot") String ot);

    @GET("existing/claim-reward")
    Call<ClaimRewards> getClaimHistory(@Header("Authorization") String apiKey,
                                       @Query("search") String profileId,
                                       @Query("page") int page,
                                       @Query("ob") String ob,
                                       @Query("ot") String ot);

    @GET("claim-reward")
    Call<ClaimRewards> getAllClaim(@Query("profile_id") int profile_id);

    @GET("axi/detail")
    Call<DetailProfile> getDetailProfile(@Header("Authorization") String apiKey);

    @GET("existing/point")
    Call<Point> getPoint(@Query("profile_id") String profile_id);

    @GET("existing/product-catalog")
    Call<Produk> getProduk(@Header("Authorization") String apiKey,
                           @Query("nama") String nama);

    @GET("existing/product-catalog")
    Call<Produk> getProdukSort(@Header("Authorization") String apiKey,
                               @Query("kategori_id") String kategori_id,
                               @Query("ob") String ob,
                               @Query("ot") String ot);

    @GET("existing/product-catalog")
    Call<Produk> getProdukAll(@Header("Authorization") String apiKey,
                              @Query("kategori_id") String kategori_id);

    @GET("existing/kategori/{id}")
    Call<DetailKategori> getDetailKategori(@Path("id") int id);

    @GET("existing/kategori/{id}")
    Call<DetailKategori> getDetailKategoriSort(@Path("id") int id,
                                               @Query("ob") String ob,
                                               @Query("ot") String ot);

    @GET("existing/status")
    Call<Status> getStatus();

    @GET("statusUBProduk")
    Call<StatusProduk> getStatusByProduk();

    @GET("statusUBTestimoni")
    Call<StatusTestimoni> getStatusByTestimoni();

    @GET("statusUBClaim")
    Call<StatusClaim> getStatusByClaim();

    @GET("semester")
    Call<Semester> getSemester();

    @GET("existing/claimReward")
    Call<ClaimReward> getClaimReward();

    @GET("testimoni")
    Call<Testimoni> getTestimoni();

    @GET("product-catalog/{id}")
    Call<DetailProduk> getDetailProduk(@Path("id") int id);

    @GET("status/{id}")
    Call<DetailStatus> getDetailStatus(@Path("id") int id);

    @GET("semester/{id}")
    Call<DetailSemester> getDetailSemester(@Path("id") int id);

    @GET("existing/claim-reward/{id}")
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

//    @POST("produk")
//    @FormUrlEncoded
//    Call<Produk> postProduk(@Field("type") String produk_type,
//                                @Field("id") String produk_id,
//                                @Field("vendor_id") String vendor_id,
//                                @Field("point") String produk_point,
//                                @Field("nama") String produk_nama,
//                                @Field("deskripsi") String produk_deskripsi,
//                                @Field("harga") String produk_harga,
//                                @Field("ppn") String produk_ppn,
//                                @Field("status_id") String produk_status,
//                                @Field("foto") String produk_foto,
//                                @Field("alt_foto") String produk_alt_foto);

    @POST("claim-reward")
    @FormUrlEncoded
    Call<DetailClaimReward> postClaimReward(
            @Field("profile_id") String profile_id,
            @Field("nama_axi") String nama_axi,
            @Field("cabang_id") String branch_id,
            @Field("nama_cabang") String nama_cabang,
            @Field("area_id") String area_id,
            @Field("nama_area") String nama_area,
            @Field("crh_id") String crh_id,
            @Field("penerima") String penerima,
            @Field("product_catalog_id") String product_catalog_id,
            @Field("ktp_npwp") String ktp_npwp,
            @Field("alamat") String alamat,
            @Field("no_po") String no_po,
            @Field("ekspedisi") String ekspedisi,
            @Field("no_resi") String no_resi,
            @Field("ongkos_kirim") String ongkos_kirim,
            @Field("harga_barang_ongkir") String harga_barang_ongkir,
            @Field("ppn") String ppn,
            @Field("status_id") String status_id,
            @Field("total_harga") String total_harga);

    @FormUrlEncoded
    @POST("claim")
    Call<PostClaimReward> postClaimReward2(
            @Field("profile_id") String profile_id,
            @Field("nama_axi") String nama_axi,
            @Field("cabang_id") String branch_id,
            @Field("nama_cabang") String nama_cabang,
            @Field("area_id") String area_id,
            @Field("nama_area") String nama_area,
            @Field("crh_id") String crh_id,
            @Field("penerima") String penerima,
            @Field("product_catalog_id") String product_catalog_id,
            @Field("ktp_npwp") String ktp_npwp,
            @Field("alamat") String alamat,
            @Field("no_po") String no_po,
            @Field("ekspedisi") String ekspedisi,
            @Field("no_resi") String no_resi,
            @Field("ongkos_kirim") String ongkos_kirim,
            @Field("harga_barang_ongkir") String harga_barang_ongkir,
            @Field("ppn") String ppn,
            @Field("status_id") String status_id,
            @Field("total_harga") String total_harga,
            @Header("Authorization") String authorization);

    @POST("existing/testimoni")
    @FormUrlEncoded
    Call<Testimoni> postTestimoni(
            @Header("Authorization") String apiKey,
            @Field("profile_id") String profile_id,
            @Field("claim_reward_id") String claim_reward_id,
            @Field("status_id") String status_id,
            @Field("testimoni") String testimoni,
            @Field("rating") String rating);

    @POST("existing/axi-foto")
    @FormUrlEncoded
    Call<Foto> postFoto(
            @Header("Authorization") String apiKey,
            @Field("axi_id") String axi_id,
            @Field("foto_ktp") String foto_ktp,
            @Field("foto_npwp") String foto_npwp,
            @Field("no_ktp") String no_ktp,
            @Field("no_npwp") String no_npwp);

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
