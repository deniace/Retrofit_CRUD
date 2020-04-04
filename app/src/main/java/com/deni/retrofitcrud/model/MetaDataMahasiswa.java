package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class MetaDataMahasiswa {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private List<DataMahasiswa> dataMahasiswa;

    public MetaDataMahasiswa(){}

    public MetaDataMahasiswa(Meta meta, List<DataMahasiswa> dataMahasiswa){
        this.meta = meta;
        this.dataMahasiswa = dataMahasiswa;
    }
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<DataMahasiswa> getDataMahasiswa() {
        return dataMahasiswa;
    }

    public void setDataMahasiswa(List<DataMahasiswa> dataMahasiswa) {
        this.dataMahasiswa = dataMahasiswa;
    }
}
