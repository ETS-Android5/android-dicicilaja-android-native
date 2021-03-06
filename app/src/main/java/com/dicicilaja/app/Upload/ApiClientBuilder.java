//ApiClientBuilder.java
package com.dicicilaja.app.Upload;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientBuilder {

    public static final String BASE_URL = "http://api.dicicilaja.com";

    private static Retrofit retrofit = null;

    public static OkHttpClient.Builder httpClient;

    public static Retrofit getClient(String baseUrl) {

        //http logging interceptor will give us the information about web service call reqponse.
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(2, TimeUnit.MINUTES);

        httpClient.readTimeout(2, TimeUnit.MINUTES);

        httpClient.writeTimeout(2, TimeUnit.MINUTES);

        httpClient.addInterceptor(logging);

        //We should add headers for the request.
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setLenient().create()))
                .client(httpClient.build())
                .build();

        return retrofit;
    }


    public static ApiClient getMGClient() {

        return getClient(BASE_URL).create(ApiClient.class);
    }

}
