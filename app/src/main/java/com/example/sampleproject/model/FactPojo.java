package com.example.sampleproject.model;

import java.util.List;

public class FactPojo {

    private String title;
    private List<RowModel> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowModel> getRows() {
        return rows;
    }

    public void setRows(List<RowModel> rows) {
        this.rows = rows;
    }

}
