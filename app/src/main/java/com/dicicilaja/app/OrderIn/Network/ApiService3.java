package com.dicicilaja.app.OrderIn.Network;

import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Data.PlatNomor.PlatNomor;
import com.dicicilaja.app.OrderIn.Data.VoucherCode.VoucherCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService3 {

    @GET("transaction/voucher-codes/search")
    Call<VoucherCode> getVoucherCode(@Query("code") String code);

    @GET("transaction/transactions/check-vehicle-id")
    Call<PlatNomor> getPlatNomor(@Query("vehicle_id") String nomor);

    @GET("profile/detail-axis")
    Call<Axi> getAxi(@Query("filter[axi_id_reff]") String id);
}
