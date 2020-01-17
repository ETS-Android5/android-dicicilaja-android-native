package com.dicicilaja.app.InformAXI.network;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Husni with ❤
 */

public class NetworkClient {

    public static final String BASE_URL = "https://dev.dicicilaja.com/";

//    public Retrofit getRetrofitInstance() {
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder
//                .setLenient()
//                .create();
//
//        return new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }

    /***
     * Retrofit with Chuck Logging Interceptor
     */
    public Retrofit getRetrofitInstance(Context mContext) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setLenient()
                .create();

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new ChuckInterceptor(mContext));

        OkHttpClient client = okHttpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
