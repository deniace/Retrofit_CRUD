package com.deni.retrofitcrud.apiinterface;

import com.deni.retrofitcrud.model.MetaDataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public interface MahasiswaInterface {
    @GET("mahasiswa")
    Call<MetaDataMahasiswa> getMahasiswa();

    @GET("mahasiswa/{npm}")
    Call<MetaDataMahasiswaSingle> getMahasiswaItem(@Path("npm") String npm);
}
