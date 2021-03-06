package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Data.Pekerjaan.Pekerjaan;
import com.dicicilaja.app.OrderIn.Data.PlatNomor.PlatNomor;
import com.dicicilaja.app.OrderIn.Data.Profile.Profile;
import com.dicicilaja.app.OrderIn.Data.Transaksi.Transaksi;
import com.dicicilaja.app.OrderIn.Data.Vehicles.Vehicles;
import com.dicicilaja.app.OrderIn.Data.VoucherCode.VoucherCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService3 {

    @GET("transaction/voucher-codes/search")
    Call<VoucherCode> getVoucherCode(@Query("code") String code);

    @GET("transaction/transactions/search/check-vehicle-id")
    Call<PlatNomor> getPlatNomor(@Query("vehicle_id") String nomor);

    @GET("profile/detail-axis/search")
    Call<Axi> getAxi(@Header("Authorization") String apiKey,
                     @Query("nomor_axi_id") String id);

    @POST("profile/profiles/order-in")
    @FormUrlEncoded
    Call<Profile> postProfil(@Header("Authorization") String apiKey,
                             @Field("nama") String name,
                             @Field("email") String email,
                             @Field("no_hp") String no_hp,
                             @Field("no_ktp") String no_ktp,
                             @Field("desa_id") String village_id,
                             @Field("jalan") String jalan);

    @Headers({
        "Accept: application/vnd.api+json"
    })

    @POST("transaction/transactions")
    @FormUrlEncoded
    Call<Transaksi> postTransaksi(@Header("Authorization") String apiKey,
                                  @Field("agen_id") String agen_id,
                                  @Field("calon_nasabah_id") String calon_nasabah_id,
                                  @Field("area_id") String area_id,
                                  @Field("cabang_id") String cabang_id,
                                  @Field("subproduk_id") String subproduk_id,
                                  @Field("produk_id") String produk_id,
                                  @Field("amount") String amount,
                                  @Field("vehicle_id") String vehicle_id,
                                  @Field("channel_id") String channel_id,
                                  @Field("voucher_code_id") String voucher_code_id,
                                  @Field("objek_brand_id") String objek_brand_id,
                                  @Field("objek_model_id") String objek_model_id,
                                  @Field("tipe_asuransi_id") String tipe_asuransi_id,
                                  @Field("jenis_angsuran_id") String jenis_angsuran_id,
                                  @Field("tenor") String tenor,
                                  @Field("jaminan_id") String jaminan_id,
                                  @Field("ktp_image") String ktp_image,
                                  @Field("bpkb") String bpkb,
                                  @Field("tahun") String tahun,
                                  @Field("qty") String qty,
                                  @Field("tempat_lahir_nasabah") String tempat_lahir_nasabah,
                                  @Field("tanggal_lahir_nasabah") String tanggal_lahir_nasabah,
                                  @Field("nama_ibu_kandung_nasabah") String nama_ibu_kandung_nasabah,
                                  @Field("janji_survey") String janji_survey,
                                  @Field("kepemilikan_npwp") String kepemilikan_npwp,
                                  @Field("pekerjaan") String  pekerjaan,
                                  @Field("jenis_kendaraan") String jenis_kendaraan);

    @GET("orderin/jobs")
    Call<List<Pekerjaan>> getPekerjaan();

    @GET("orderin/vehicles/{vehicles_type}")
    Call<List<Vehicles>> getVehicles(@Path("vehicles_type") String vehicles_type);
}
