package com.dicicilaja.app.API.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ziterz on 1/15/2018.
 */

public class ClientDetailPengajuanStatus {
    public static final String BASE_URL = "https://api.dicicilaja.com";
    private static Retrofit retrofit = null;


    public static Retrofit getClientDetailPengajuanStatus() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
