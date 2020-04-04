package com.deni.retrofitcrud.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.RetrofitInstance;
import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMhsGet1Binding;
import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Deni Supriyatna on 11 - Mar - 2020.
 * email : denisupriyatna01@gmail.com
 */

public class MhsGet1Activity extends AppCompatActivity {

    private String TAG = "MhsGet1Activity";
    ActivityMhsGet1Binding binding;
    Retrofit retrofit;
    private DataMahasiswa dataMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mhs_get1);
        binding.setActivity(this);
        dataMahasiswa = new DataMahasiswa();
        binding.setDataMahasiswa(dataMahasiswa);
        retrofit = RetrofitInstance.getRetrofit(this);
        populateData();
    }

    private void populateData(){
        String npm = "123";
        MahasiswaInterface mahasiswaInterface = retrofit.create(MahasiswaInterface.class);
        Call<MetaDataMahasiswaSingle> call = mahasiswaInterface.getMahasiswaItem(npm);

        call.enqueue(new Callback<MetaDataMahasiswaSingle>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                if (response.isSuccessful()){
                    dataMahasiswa = response.body().getDataMahasiswa();
                    binding.setDataMahasiswa(dataMahasiswa);
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
            public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                Log.d(TAG, "onFailure: failure");

                t.printStackTrace();
            }
        });
    }
}
