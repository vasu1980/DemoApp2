package com.example.sampleproject.net;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface IApiInterface {
    @Headers("Connection:close")
    @GET
    Call<ResponseBody> getAPI(@Url String url);

    @POST()
    Call<ResponseBody> PostAPI(@Url String url, @Body RequestBody body);

    @POST()
    Call<ResponseBody> PostJSONAPI(@Url String url, @Body RequestBody body);
}

