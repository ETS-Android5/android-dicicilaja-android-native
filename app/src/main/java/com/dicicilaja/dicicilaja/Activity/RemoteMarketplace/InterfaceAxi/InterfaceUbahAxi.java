package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public interface InterfaceUbahAxi {
    @Headers({
            "Accept: application/json",
    })
    @POST("axi/edit")
    @FormUrlEncoded
    Call<UbahAxi> change(@Header("Authorization") String apiKey,
                         @Field("namaLengkap") String namaLengkap,
                         @Field("tempatLahir") String tempatLahir,
                         @Field("tanggal") String tanggal,
                         @Field("noHp") String noHp,
                         @Field("email") String email,
                         @Field("alamat") String alamat,
                         @Field("rtRw") String rtRw,
                         @Field("kelurahan") String kelurahan,
                         @Field("kecamatan") String kecamatan,
                         @Field("provinsi") String provinsi,
                         @Field("kodepos") String kodepos,
                         @Field("jk") String jk,
                         @Field("noNpwp") String noNpwp,
                         @Field("namaBank") String namaBank,
                         @Field("cabangBank") String cabangBank,
                         @Field("noRekening") String noRekening,
                         @Field("anRekening") String anRekening,
                         @Field("kotaBank") String kotaBank);
}