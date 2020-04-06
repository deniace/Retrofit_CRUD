package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Deni Supriyatna (deni ace) on 06 - 04 - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class DataPaging {
    @SerializedName("total_data")
    @Expose
    private Integer totalData;
    @SerializedName("rows")
    @Expose
    private List<DataMahasiswa> rowPaging;

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public List<DataMahasiswa> getRowPaging() {
        return rowPaging;
    }

    public void setRowPaging(List<DataMahasiswa> rowPaging) {
        this.rowPaging = rowPaging;
    }
}
