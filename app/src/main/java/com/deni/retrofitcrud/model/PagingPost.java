package com.deni.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Deni Supriyatna (deni ace) on 06 - 04 - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class PagingPost {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("page_size")
    @Expose
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
