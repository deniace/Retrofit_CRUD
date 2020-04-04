package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Deni Supriyatna on 11 - Mar - 2020.
 * email : denisupriyatna01@gmail.com
 */
public class MetaDataMahasiswaSingle {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private DataMahasiswa dataMahasiswa;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public DataMahasiswa getDataMahasiswa() {
        return dataMahasiswa;
    }

    public void setDataMahasiswa(DataMahasiswa dataMahasiswa) {
        this.dataMahasiswa = dataMahasiswa;
    }
}
