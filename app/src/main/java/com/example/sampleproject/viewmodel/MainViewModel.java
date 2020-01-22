package com.example.sampleproject.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sampleproject.R;
import com.example.sampleproject.adapter.FactsAdapter;
import com.example.sampleproject.model.FactsData;
import com.example.sampleproject.model.RowModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private FactsData factsData;
    public MutableLiveData<RowModel> selected;
    private FactsAdapter adapter;
    Context ctx;

    public void init(Context ctx) {
        this.ctx=ctx;
        factsData = new FactsData();
        selected = new MutableLiveData<>();
        adapter = new FactsAdapter(ctx,R.layout.view_facts_item, this);

    }


    public void fetchList() {
        factsData.fetchList();
    }

    public String getTitle() {
       return factsData.getTitle();
    }
    public Boolean isLoading(){
        return  factsData.getLoadingStatus();
    }
    public MutableLiveData<List<RowModel>> getList() {
        return factsData.getRows();
    }

    public FactsAdapter getAdapter() {
        return adapter;
    }

    public void setDataInAdapter(List<RowModel> rows) {
        this.adapter.setRows(rows);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<RowModel> getSelected() {
        return selected;
    }

    public void onItemClick(RowModel row) {
        selected.setValue(row);
    }
}

