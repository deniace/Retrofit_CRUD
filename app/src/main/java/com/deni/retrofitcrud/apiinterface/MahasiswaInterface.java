package com.deni.retrofitcrud.apiinterface;

import com.deni.retrofitcrud.model.MetaDataMahasiswa;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 */
public interface MahasiswaInterface {
    @GET("mahasiswa")
    Call<MetaDataMahasiswa> getMahasiswa();
}
