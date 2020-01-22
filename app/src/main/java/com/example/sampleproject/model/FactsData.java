package com.example.sampleproject.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.example.sampleproject.net.APIUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactsData extends BaseObservable {
    String baseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    String strUrl = "facts.json";
    String title = "";
    List<RowModel> rowsList = new ArrayList<>();
    MutableLiveData<List<RowModel>> rowsLiveData = new MutableLiveData<>();
    private boolean isLoading;


    public void addRows(RowModel row) {
        rowsList.add(row);
    }

    public void setTitle(FactPojo pojo) {
        title = pojo.getTitle();

    }
    public Boolean isLoading(boolean flag) {
        isLoading=flag;
        return isLoading;

    }
    public  Boolean getLoadingStatus(){
        return isLoading;
    }

    public String getTitle() {
        return title;
    }

    public MutableLiveData<List<RowModel>> getRows() {
        return rowsLiveData;
    }

    public List<RowModel> getRowsList() {
        return rowsList;
    }

    public void fetchList() {
        isLoading(true);
        Call<ResponseBody> call = APIUtils.getAPI(strUrl, baseUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.isSuccessful() && response.code() == 200) {
                        String responseRecieved = response.body().string();
                        System.out.println(responseRecieved);
                        FactPojo model = new Gson().fromJson(responseRecieved, FactPojo.class);
                        setTitle(model);
                        isLoading(false);
                        rowsLiveData.setValue(model.getRows());
                   } else {
                        System.out.println("Internal Error");
                    }
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


                System.out.println(t.getMessage());
            }
        });
    }
}

