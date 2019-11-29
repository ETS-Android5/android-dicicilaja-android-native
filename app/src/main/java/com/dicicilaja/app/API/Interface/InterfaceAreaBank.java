package com.dicicilaja.app.API.Interface;

import com.dicicilaja.app.API.Model.AreaBankRequest.Bank.Bank;
import com.dicicilaja.app.API.Model.AreaBankRequest.Desa.Desa;
import com.dicicilaja.app.API.Model.AreaBankRequest.Kota.Kota;
import com.dicicilaja.app.API.Model.AreaBankRequest.Distrik.Distrik;
import com.dicicilaja.app.API.Model.AreaBankRequest.Provinsi.Provinsi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fawazrifqi on 22/04/18.
 */

public interface InterfaceAreaBank {

    @GET("api/banks")
    Call<Bank> getBanks();

    @GET("api/provinsi")
    Call<Provinsi> getProvinsi();

    @GET("api/kota")
    Call<Kota> getKota(@Query("provinsi_id") String provinsi_id);

    @GET("api/distrik")
    Call<Distrik> getDistrik(@Query("kota_id") String kota_id);

    @GET("api/desa")
    Call<Desa> getDesa(@Query("distrik_id") String distrik_id);
}
