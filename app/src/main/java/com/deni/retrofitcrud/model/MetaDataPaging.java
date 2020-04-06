package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Deni Supriyatna (deni ace) on 06 - 04 - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class MetaDataPaging {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private DataPaging dataPaging;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public DataPaging getDataPaging() {
        return dataPaging;
    }

    public void setDataPaging(DataPaging dataPaging) {
        this.dataPaging = dataPaging;
    }
}
