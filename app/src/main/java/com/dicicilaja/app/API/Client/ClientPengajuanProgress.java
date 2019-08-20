package com.dicicilaja.app.API.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ziterz on 1/25/2018.
 */

public class ClientPengajuanProgress {
    public static final String BASE_URL = "https://api.dicicilaja.com";
    private static Retrofit retrofit = null;


    public static Retrofit getClientPengajuanProgress() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
