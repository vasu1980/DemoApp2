package com.example.sampleproject.net;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class APIUtils {

    public static <T> List<T> toList(String json, Class<T> clas) {
        return new Gson().fromJson(json, new ListOfJson<T>(clas));
    }

    public static Call<ResponseBody> getAPI(String strUrl, String baseUrl){
        IApiInterface apiService = ApiClient.getClient(baseUrl).create(IApiInterface.class);
        return apiService.getAPI(strUrl);
    }

    public static Call<ResponseBody> postAPI(String strUrl, RequestBody requestBody, String baseUrl){
        IApiInterface apiService = ApiClient.getClient(baseUrl).create(IApiInterface.class);
        return apiService.PostAPI(strUrl,requestBody);
    }

    public static Call<ResponseBody> postJSONAPI(String strUrl, RequestBody requestBody, String baseUrl){
        IApiInterface apiService = ApiClient.getClient(baseUrl).create(IApiInterface.class);
        return apiService.PostJSONAPI(strUrl,requestBody);
    }
}
