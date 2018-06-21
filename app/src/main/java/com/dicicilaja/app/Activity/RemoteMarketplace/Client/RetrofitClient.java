package com.dicicilaja.app.Activity.RemoteMarketplace.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://api.dicicilaja.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
