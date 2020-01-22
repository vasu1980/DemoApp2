package com.example.sampleproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.R;
import com.example.sampleproject.model.RowModel;
import com.example.sampleproject.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.GenericViewHolder> {


    private int layoutId;
    private List<RowModel> rows;
    MainViewModel viewModel;
    Context ctx;

    public FactsAdapter(Context ctx, @LayoutRes int layoutId, MainViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
        this.ctx = ctx;
    }


    private RowModel getObjForPosition(int position) {
        return rows.get(position);
    }


    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return rows == null ? 0 : rows.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);

        return new GenericViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        RowModel obj = getObjForPosition(position);
        holder.title.setText(obj.getTitle());
        holder.description.setText(obj.getDescription());
        if (obj.getImageHref() != null && obj.getImageHref() != "") {
            Picasso.get().load(obj.getImageHref()).error(R.drawable.no_image).into(holder.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setRows(List<RowModel> rows) {
        this.rows = rows;
    }


    class GenericViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imageView;

        GenericViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);

        }


    }
}

