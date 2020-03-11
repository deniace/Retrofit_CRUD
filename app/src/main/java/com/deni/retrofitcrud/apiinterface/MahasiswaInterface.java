package com.deni.retrofitcrud.apiinterface;

import com.deni.retrofitcrud.model.MetaDataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 */
public interface MahasiswaInterface {
    @GET("mahasiswa")
    Call<MetaDataMahasiswa> getMahasiswa();

    @GET("mahasiswa/{npm}")
    Call<MetaDataMahasiswaItem> getMahasiswaItem(@Path("npm") String npm);
}
