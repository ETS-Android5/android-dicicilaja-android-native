package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Data.PlatNomor.PlatNomor;
import com.dicicilaja.app.OrderIn.Data.Profile.Profile;
import com.dicicilaja.app.OrderIn.Data.Transaksi.Transaksi;
import com.dicicilaja.app.OrderIn.Data.VoucherCode.VoucherCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService3 {

    @GET("transaction/voucher-codes/search")
    Call<VoucherCode> getVoucherCode(@Query("code") String code);

    @GET("transaction/transactions/check-vehicle-id")
    Call<PlatNomor> getPlatNomor(@Query("vehicle_id") String nomor);

    @GET("profile/detail-axis")
    Call<Axi> getAxi(@Query("filter[axi_id_reff]") String id,
                     @Query("include") String param);

    @POST("profile/profiles")
    Call<Profile> postProfil(@Query("name") String name,
                             @Query("email") String email,
                             @Query("no_hp") String no_hp,
                             @Query("no_ktp") String no_ktp,
                             @Query("village_id") String village_id,
                             @Query("jalan") String jalan);

    @POST("transaction/transactions")
    Call<Transaksi> postTransaksi(@Query("agen_id") String agen_id,
                                  @Query("calon_nasabah_id") String calon_nasabah_id,
                                  @Query("area_id") String area_id,
                                  @Query("cabang_id") String cabang_id,
                                  @Query("subproduk_id") String subproduk_id,
                                  @Query("program_id") String program_id,
                                  @Query("amount") String amount,
                                  @Query("status") String status,
                                  @Query("vehicle_id") String vehicle_id,
                                  @Query("channel_id") String channel_id,
                                  @Query("voucher_code_id") String voucher_code_id,
                                  @Query("objek_brand_id") String objek_brand_id,
                                  @Query("objek_model_id") String objek_model_id,
                                  @Query("tipe_asuransi_id") String tipe_asuransi_id,
                                  @Query("jenis_angsuran") String jenis_angsuran,
                                  @Query("tenor") String tenor,
                                  @Query("ktp_image") String ktp_image,
                                  @Query("bpkb") String bpkb);
}
