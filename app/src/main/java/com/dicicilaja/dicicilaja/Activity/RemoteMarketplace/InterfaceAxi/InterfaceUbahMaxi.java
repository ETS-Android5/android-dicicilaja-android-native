package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahMaxi.UbahMaxi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public interface InterfaceUbahMaxi {
    @Headers({
            "Accept: application/json",
    })
    @POST("jodi/changemaxi")
    @FormUrlEncoded

    Call<UbahMaxi> change(@Header("Authorization") String apiKey,
                          @Field("jk") String jk,
                          @Field("namaPerusahaan") String namaPerusahaan,
                          @Field("alamatPerusahaan") String alamatPerusahaan,
                          @Field("NPWPPerusahaan") String NPWPPerusahaan,
                          @Field("namaPemilik") String namaPemilik,
                          @Field("alamatEmail") String alamatEmail,
                          @Field("telephone") String telephone,
                          @Field("handphone") String handphone,
                          @Field("alamatPemilik") String alamatPemilik,
                          @Field("kelurahan") String kelurahan,
                          @Field("kota") String kota,
                          @Field("KTPPemilik") String KTPPemilik,
                          @Field("NPWPPemilik") String NPWPPemilik);
}
