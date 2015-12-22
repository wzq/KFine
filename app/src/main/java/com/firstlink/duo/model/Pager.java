package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wzq on 15/6/2.
 */
public class Pager {

    @SerializedName("start_row")
    private int startRow;

    @SerializedName("page_size")
    private int pageSize;

    private int total;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
