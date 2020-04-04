package com.deni.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMainBinding;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;
import com.deni.retrofitcrud.view.MhsGet1Activity;
import com.deni.retrofitcrud.view.MhsGetAllActivity;

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
        binding.setActivity(this);
        retrofit = RetrofitInstance.getRetrofit(this);
        binding.btnGet.setOnClickListener(getClick);
    }

    private View.OnClickListener getClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getData();
        }
    };

    private void getData(){
        MahasiswaInterface mahasiswaInterface = retrofit.create(MahasiswaInterface.class);
        Call<MetaDataMahasiswaSingle> result = mahasiswaInterface.getMahasiswaItem("43A87006150265");

        result.enqueue(new Callback<MetaDataMahasiswaSingle>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                if (response.isSuccessful()){
                    if (response.body().getMeta().isSuccess()){
                        binding.tvHelloWorld.setText(response.body().getDataMahasiswa().getNamaMahasiswa());
                    }else{
                        binding.tvHelloWorld.setText(response.body().getMeta().getMessage());
                    }
                }else{
                    Toast.makeText(MainActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    /**
     * untuk pindah ke activity get 1
     * @param view btn_get1
     */
    public void pindahGet1(View view){
        Intent intent = new Intent(MainActivity.this, MhsGet1Activity.class);
        startActivity(intent);
    }

    /**
     * pindah ke activity mhsgetall
     * @param view btn_get_all
     */
    public void goToGetAll(View view){
        Intent intent = new Intent(MainActivity.this, MhsGetAllActivity.class);
        startActivity(intent);
    }
}
