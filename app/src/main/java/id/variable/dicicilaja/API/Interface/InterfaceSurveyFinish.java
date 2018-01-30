package id.variable.dicicilaja.API.Interface;

import id.variable.dicicilaja.Model.ResRequestProcess;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ziterz on 1/30/2018.
 */

public interface InterfaceSurveyFinish {
    @Headers({
            "Accept: application/json",
    })
    @POST("assign")
    @FormUrlEncoded
    Call<ResRequestProcess> assign(@Header("Authorization") String apiKey,
                                   @Field("transaction_id") String transaction_id,
                                   @Field("assigned_id") String assigned_id,
                                   @Field("notes") String notes,
                                   @Field("reschedule_date") String reschedule_date,
                                   @Field("ktp_suami1") String ktp_suami1,
                                   @Field("ktp_penjamin") String ktp_penjamin,
                                   @Field("surat_cerai") String surat_cerai,
                                   @Field("surat_kematian") String surat_kematian,
                                   @Field("surat_domisili") String surat_domisili,
                                   @Field("kartu_keluarga") String kartu_keluarga,
                                   @Field("bukti_kepemilikan_rumah") String bukti_kepemilikan_rumah,
                                   @Field("bukti_penghasilan") String bukti_penghasilan,
                                   @Field("no_rangka") String no_rangka,
                                   @Field("stnk") String stnk,
                                   @Field("bpkb") String bpkb);
}
