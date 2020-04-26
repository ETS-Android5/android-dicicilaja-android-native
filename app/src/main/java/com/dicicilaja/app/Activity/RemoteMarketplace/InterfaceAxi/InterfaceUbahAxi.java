package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import com.dicicilaja.app.InformAXI.model.EditShProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
                         @Field("noHp") String noHp,
                         @Field("email") String email,
                         @Field("alamat") String alamat,
                         @Field("NPWP") String NPWP,
                         @Field("namaBank") String namaBank,
                         @Field("cabang") String cabang,
                         @Field("rekening") String rekening,
                         @Field("AN") String AN,
                         @Field("kotaBank") String kotaBank);

    @FormUrlEncoded
    @POST("sh/edit")
    Call<EditShProfile>
    editProfile(@Header("Authorization") String authorization,
                @Field("name") String name,
                @Field("phone") String phone,
                @Field("email") String email);
}