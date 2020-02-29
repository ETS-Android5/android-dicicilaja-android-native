package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateMitra.CreateMitra;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceCreateMitra {
    @Headers({
            "Accept: application/json",
    })
    @POST("maxi/registration")
    @FormUrlEncoded
    Call<CreateMitra> create(@Header("Authorization") String apiKey,
                             @Field("nama_perusahaan") String company,
                             @Field("nama") String owner,
                             @Field("jenis_kelamin") String gender,
                             @Field("alamat_jalan_pribadi") String address,
                             @Field("no_hp") String handphone,
                             @Field("no_telp") String telphone,
                             @Field("no_ktp") String ktp,
                             @Field("no_npwp_pribadi") String personal_npwp,
                             @Field("no_npwp_perusahaan") String company_npwp,
                             @Field("desa_id") String desa,
                             @Field("alamat_jalan_perusahaan") String company_address,
                             @Field("email") String email,
                             @Field("nama_program") String program,
                             @Field("produk_id") String program_id,
                             @Field("foto_ktp") String foto_ktp,
                             @Field("foto_npwp") String foto_npwp);
}
