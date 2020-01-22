package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.sampleproject.model.RowModel;

import com.example.sampleproject.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupBindings(savedInstanceState);
    }


    private void setupBindings(Bundle savedInstanceState) {
        pb = findViewById(R.id.pb);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init(this);
        }

        recyclerView.setAdapter(viewModel.getAdapter());
        setupListUpdate();


    }

    private void setupListUpdate() {


        viewModel.fetchList();
        if (viewModel.isLoading()) {
            pb.setVisibility(View.VISIBLE);
        }

        viewModel.getList().observe(this, new Observer<List<RowModel>>() {
            @Override
            public void onChanged(List<RowModel> rows) {

                viewModel.setDataInAdapter(rows);
                if (!viewModel.isLoading()) {
                    pb.setVisibility(View.GONE);
                }else{
                    pb.setVisibility(View.VISIBLE);
                }
                String title = viewModel.getTitle();
                getSupportActionBar().setTitle(title);
            }
        });
        setupListClick();


    }

    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<RowModel>() {
            @Override
            public void onChanged(RowModel rowModel) {
                if (rowModel != null) {
                    Toast.makeText(MainActivity.this, "You selected a " + rowModel.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
