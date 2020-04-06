package com.deni.retrofitcrud.apiinterface;

import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;
import com.deni.retrofitcrud.model.MetaDataPaging;
import com.deni.retrofitcrud.model.PagingPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("mahasiswa/")
    Call<MetaDataMahasiswaSingle> postMahasiswa(@Body DataMahasiswa dataMahasiswa);

    @POST("mahasiswa/paging")
    Call<MetaDataPaging> getPagingMahasiswa(@Body PagingPost pagingPost);

    @PUT("mahasiswa/")
    Call<MetaDataMahasiswaSingle> putMahasiswa(@Body DataMahasiswa dataMahasiswa);

    @DELETE("mahasiswa/{npm}")
    Call<MetaDataMahasiswaSingle> deleteMahasiswaItem(@Path("npm") String npm);
}
