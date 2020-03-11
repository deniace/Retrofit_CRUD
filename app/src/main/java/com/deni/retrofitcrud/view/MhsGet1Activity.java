package com.deni.retrofitcrud.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.RetrofitInstance;
import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMhsGet1Binding;
import com.deni.retrofitcrud.model.MetaDataMahasiswaItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Deni Supriyatna on 11 - Mar - 2020.
 */

public class MhsGet1Activity extends AppCompatActivity {

    private String TAG = "LOOOOOGG";
    ActivityMhsGet1Binding binding;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mhs_get1);
        retrofit = RetrofitInstance.getRetrofit(this);
        populateData();
    }

    private void populateData(){
        String npm = "123";
        MahasiswaInterface mahasiswaInterface = retrofit.create(MahasiswaInterface.class);
        Call<MetaDataMahasiswaItem> call = mahasiswaInterface.getMahasiswaItem(npm);

        call.enqueue(new Callback<MetaDataMahasiswaItem>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswaItem> call, Response<MetaDataMahasiswaItem> response) {
                if (response.isSuccessful()){
                    binding.tietMetaSuccess.setText(response.body().getMeta().getSuccess());
                    binding.tietMetaStatus.setText(response.body().getMeta().getStatusString());
                    binding.tietMetaMessage.setText(response.body().getMeta().getMessage());
                    binding.tietMetaTimestamp.setText(response.body().getMeta().getTimeStampString());

                    binding.tietDataNpm.setText(response.body().getDataMahasiswa().getNpm());
                    binding.tietDataNama.setText(response.body().getDataMahasiswa().getNamaMahasiswa());
                    binding.tietDataJk.setText(response.body().getDataMahasiswa().getJenisKelamin());
                    binding.tietDataJurusan.setText(response.body().getDataMahasiswa().getJurusan());
                    binding.tietDataNoHp.setText(response.body().getDataMahasiswa().getNoHp());
                    binding.tietDataEmail.setText(response.body().getDataMahasiswa().getEmail());
                    binding.tietDataAgama.setText(response.body().getDataMahasiswa().getAgama());
                }
            }

            @Override
            public void onFailure(Call<MetaDataMahasiswaItem> call, Throwable t) {
                Log.d(TAG, "onFailure: failure");

                t.printStackTrace();
            }
        });
    }
}
