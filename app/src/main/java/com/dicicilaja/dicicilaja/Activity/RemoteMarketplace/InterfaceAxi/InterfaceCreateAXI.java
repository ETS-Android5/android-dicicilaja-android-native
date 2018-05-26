package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;

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
    @POST("/axi/register")
    @FormUrlEncoded
    Call<CreateAXI> create(@Header("Authorization") String apiKey,
                           @Field("areaId") String areaId,
                           @Field("branchId") String branchId,
                           @Field("axiReff") String axiReff,
                           @Field("namaLengkap") String namaLengkap,
                           @Field("noKTP") String noKTP,
                           @Field("tempatLahir") String tempatLahir,
                           @Field("tanggal") String tanggal,
                           @Field("statusPerkawinan") String statusPerkawinan,
                           @Field("alamat") String alamat,
                           @Field("rtRw") String rtRw,
                           @Field("provinsi") String provinsi,
                           @Field("kota") String kota,
                           @Field("kecamatan") String kecamatan,
                           @Field("kelurahan") String kelurahan,
                           @Field("kodePos") String kodePos,
                           @Field("jk") String jk,
                           @Field("email") String email,
                           @Field("noHp") String noHp,
                           @Field("namaIbu") String namaIbu,
                           @Field("namaBank") String namaBank,
                           @Field("noRekening") String noRekening,
                           @Field("cabangBank") String cabangBank,
                           @Field("anRekening") String anRekening,
                           @Field("kotaBank") String kotaBank,
                           @Field("noNpwp") String noNpwp,
                           @Field("namaNpwp") String namaNpwp,
                           @Field("statusNpwp") String statusNpwp,
                           @Field("pkpStatus") String pkpStatus,
                           @Field("imageKtp") String imageKtp,
                           @Field("imageNpwp") String imageNpwp,
                           @Field("imageCover") String imageCover);
}