package com.dicicilaja.app.InformAXI.network;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Husni with ❤
 */

public class NetworkClient {

    public static final String BASE_URL = "https://dev.dicicilaja.com/";

    public Retrofit getRetrofitInstance() {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(
                                        new HttpLoggingInterceptor()
                                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                                )
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
