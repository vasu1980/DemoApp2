package com.example.sampleproject.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static Retrofit getClient(String baseUrl) {

        Retrofit retrofit = null;

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                 .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }}
