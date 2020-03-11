package com.deni.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMainBinding;
import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Deni Supriyatna on 10 - Mar - 2020.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        retrofit = RetrofitInstance.getRetrofit(this);
        binding.btnGet.setOnClickListener(getClick);
    }

    private View.OnClickListener getClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            get();
        }
    };

    private void get(){
        MahasiswaInterface mahasiswaInterface = retrofit.create(MahasiswaInterface.class);
        Call<MetaDataMahasiswa> result = mahasiswaInterface.getMahasiswa();

        result.enqueue(new Callback<MetaDataMahasiswa>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswa> call, Response<MetaDataMahasiswa> response) {
                if (response.body().getMeta().isSuccess()){
//                    binding.tvHelloWorld.setText(response.body().getDataMahasiswa());
                    List<DataMahasiswa> dataMahasiswaList = response.body().getDataMahasiswa();
                    binding.tvHelloWorld.setText(dataMahasiswaList.get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<MetaDataMahasiswa> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
