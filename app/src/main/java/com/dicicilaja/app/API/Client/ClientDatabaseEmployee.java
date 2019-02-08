package com.dicicilaja.app.API.Client;

import com.dicicilaja.app.Utils.RetrofitLoggingInterceptor;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ziterz on 1/9/2018.
 */

public class ClientDatabaseEmployee {

    public static final String BASE_URL = "https://api.dicicilaja.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClientDatabaseEmployee() {
        if (retrofit==null) {

            Cache cache = null;

            RetrofitLoggingInterceptor logging = new RetrofitLoggingInterceptor();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
