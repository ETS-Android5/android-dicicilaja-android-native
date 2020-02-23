package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceCreateAXI {
    @Headers({
            "Accept: application/json",
    })
    @POST("axi/registration")
    @FormUrlEncoded
    Call<CreateAXI> create(@Header("Authorization") String apiKey,
                           @Field("area_id") String area,
                           @Field("cabang_daftar") String cabang,
                           @Field("axi_reff") String axi_reff,
                           @Field("nama") String name,
                           @Field("no_ktp") String no_ktp,
                           @Field("kota_lahir") String tempat_lahir,
                           @Field("tanggal_lahir") String tanggal_lahir,
                           @Field("status_perkawinan") String status_perkawinan,
                           @Field("alamat_ktp") String alamat_ktp,
                           @Field("rt") String rt,
                           @Field("rw") String rw,
                           @Field("provinsi_id") String provinsi,
                           @Field("kota_id") String kabupaten,
                           @Field("kecamatan_id") String kecamatan,
                           @Field("kelurahan_id") String kelurahan,
                           @Field("kode_pos") String kode_pos,
                           @Field("jenis_kelamin") String jenis_kelamin,
                           @Field("email") String email,
                           @Field("no_hp") String no_hp,
                           @Field("nama_ibu_kandung") String nama_ibu_kandung,
                           @Field("nama_bank") String nama_bank,
                           @Field("no_rekening") String no_rekening,
                           @Field("cabang_bank") String cabang_bank,
                           @Field("an_rekening") String an_rekening,
                           @Field("kota_bank") String kota_bank,
                           @Field("no_npwp") String npwp_no,
                           @Field("nama_npwp") String nama_npwp,
                           @Field("tipe_npwp") String status_npwp,
                           @Field("pkp") String pkp_status,
                           @Field("image_ktp") String image_ktp,
                           @Field("image_npwp") String image_npwp,
                           @Field("image_tabungan") String image_tabungan,
                           @Field("bank_id") String kode_bank);
}