package com.deni.retrofitcrud;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 */
public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://192.168.43.190/banisaleh/";

    public static Retrofit getRetrofit(Context context){
        if(retrofit == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
