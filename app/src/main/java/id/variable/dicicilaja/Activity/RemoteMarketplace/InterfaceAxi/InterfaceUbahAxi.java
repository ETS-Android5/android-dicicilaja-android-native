package id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi;

import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;
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
    @POST("jodi/changeaxi")
    @FormUrlEncoded
    Call<UbahAxi> change(@Header("Authorization") String apiKey,
                         @Field("nama_lengkap") String nama_lengkap,
                         @Field("tempat_lahir") String tempat_lahir,
                         @Field("tanggal_lahir") String tanggal_lahir,
                         @Field("no_hp") String no_hp,
                         @Field("email") String email,
                         @Field("alamat_ktp") String alamat_ktp,
                         @Field("rt_rw_ktp") String rt_rw_ktp,
                         @Field("kelurahan_ktp") String kelurahan_ktp,
                         @Field("kecamatan_ktp") String kecamatan_ktp,
                         @Field("provinsi_ktp") String provinsi_ktp,
                         @Field("kodepos_ktp") String kodepos_ktp,
                         @Field("jenis_kelamin") String jenis_kelamin,
                         @Field("no_npwp") String no_npwp,
                         @Field("nama_bank") String nama_bank,
                         @Field("no_rekening") String no_rekening,
                         @Field("cabang_bank") String cabang_bank,
                         @Field("an_rekening") String an_rekening,
                         @Field("kota_bank") String kota_bank);
}
