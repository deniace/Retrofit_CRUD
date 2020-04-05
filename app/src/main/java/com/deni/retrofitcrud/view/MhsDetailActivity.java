package com.deni.retrofitcrud.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.RetrofitInstance;
import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMhsDetailBinding;
import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MhsDetailActivity extends AppCompatActivity {

    private DataMahasiswa dataMahasiswa;
    private int state;
    private ActivityMhsDetailBinding binding;
    private MahasiswaInterface mahasiswaInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mhs_detail);
        binding.setActivity(this);
        mahasiswaInterface = RetrofitInstance.getRetrofit(this).create(MahasiswaInterface.class);
        state = getIntent().getIntExtra("STATE", 0);
        if(state > 0){
            String npm = getIntent().getStringExtra("NPM");
            populateData(npm);
        }else {
            binding.btnHapusMhsdetail.setVisibility(View.GONE);
            dataMahasiswa = new DataMahasiswa();
            binding.setDataMhs(dataMahasiswa);
        }
        setTitle("Mahasiswa Detail");
    }

    public void saveData(View view){
        nullError();
        if(isDataValid()){
            binding.pbMshDetail.setVisibility(View.VISIBLE);
            Call<MetaDataMahasiswaSingle> call = state == 0 ? mahasiswaInterface.postMahasiswa(dataMahasiswa) : mahasiswaInterface.putMahasiswa(dataMahasiswa);

            call.enqueue(new Callback<MetaDataMahasiswaSingle>() {
                @Override
                public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                    if(response.isSuccessful()){
                        if(response.body().getMeta().isSuccess()){
                            binding.pbMshDetail.setVisibility(View.GONE);
                            Toast.makeText(MhsDetailActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("NPM", dataMahasiswa.getNpm());
                            intent.putExtra("ACTION", state == 0 ? "NEW" : "UPDATE");
                            setResult(RESULT_OK, intent);
                            finish();
                        }else{
                            binding.pbMshDetail.setVisibility(View.GONE);
                            Toast.makeText(MhsDetailActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        binding.pbMshDetail.setVisibility(View.GONE);
                        Toast.makeText(MhsDetailActivity.this, R.string.error_send_data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                    t.printStackTrace();
                    binding.pbMshDetail.setVisibility(View.GONE);
                    Toast.makeText(MhsDetailActivity.this, R.string.error_send_data, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isDataValid(){
        boolean valid = false;
        if(binding.tietNpmMhsDetail.length() <1){
            binding.tilNpmMhsDetail.setErrorEnabled(true);
            binding.tilNpmMhsDetail.setError("Npm Harus di isi");
            binding.tietNpmMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietNamaMhsDetail.length()<1){
            binding.tilNamaMhsDetail.setErrorEnabled(true);
            binding.tilNamaMhsDetail.setError("Nama Harus di isi");
            binding.tietNamaMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietJkMhsDetail.length()<1){
            binding.tilJkMhsDetail.setErrorEnabled(true);
            binding.tilJkMhsDetail.setError("Jenis Kelamin Harus di isi");
            binding.tietJkMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietJurusanMhsDetail.length() < 1){
            binding.tilJurusanMhsDetail.setErrorEnabled(true);
            binding.tilJurusanMhsDetail.setError("Jurusan Harus di isi");
            binding.tietJurusanMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietNoHpMhsDetail.length() < 1){
            binding.tilNoHpMhsDetail.setErrorEnabled(true);
            binding.tilNoHpMhsDetail.setError("Nomor Hp harus di isi");
            binding.tietNoHpMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietEmailMhsDetail.length() < 1){
            binding.tilEmailMhsDetail.setErrorEnabled(true);
            binding.tilEmailMhsDetail.setError("Email Harus di isi");
            binding.tietEmailMhsDetail.requestFocus();
            valid = false;
        }else if(binding.tietAgamaMhsDetail.length() < 1){
            binding.tilAgamaMhsDetail.setErrorEnabled(true);
            binding.tilAgamaMhsDetail.setError("Agama harus di isi");
            binding.tietAgamaMhsDetail.requestFocus();
            valid = false;
        }else{
            valid = true;
        }
        return valid;
    }

    private void nullError(){
        binding.tilNpmMhsDetail.setErrorEnabled(false);
        binding.tilNpmMhsDetail.setError(null);
        binding.tilNamaMhsDetail.setErrorEnabled(false);
        binding.tilNamaMhsDetail.setError(null);
        binding.tilJkMhsDetail.setErrorEnabled(false);
        binding.tilJkMhsDetail.setError(null);
        binding.tilJurusanMhsDetail.setErrorEnabled(false);
        binding.tilJurusanMhsDetail.setError(null);
        binding.tilNoHpMhsDetail.setErrorEnabled(false);
        binding.tilNoHpMhsDetail.setError(null);
        binding.tilEmailMhsDetail.setErrorEnabled(false);
        binding.tilEmailMhsDetail.setError(null);
        binding.tilAgamaMhsDetail.setErrorEnabled(false);
        binding.tilAgamaMhsDetail.setError(null);
    }

    private void populateData(String npm){
        binding.pbMshDetail.setVisibility(View.GONE);

        Call<MetaDataMahasiswaSingle> call = mahasiswaInterface.getMahasiswaItem(npm);

        call.enqueue(new Callback<MetaDataMahasiswaSingle>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                if(response.isSuccessful()){
                    if(response.body().getMeta().isSuccess()){
                        dataMahasiswa = response.body().getDataMahasiswa();
                        binding.setDataMhs(dataMahasiswa);
                        binding.pbMshDetail.setVisibility(View.GONE);
                    }else{
                        binding.pbMshDetail.setVisibility(View.GONE);
                        Toast.makeText(MhsDetailActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    binding.pbMshDetail.setVisibility(View.GONE);
                    Toast.makeText(MhsDetailActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                t.printStackTrace();
                binding.pbMshDetail.setVisibility(View.GONE);
                Toast.makeText(MhsDetailActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void delete(View view){
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_confirmation)
                .setMessage(R.string.you_sure)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        prosesDelete();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void prosesDelete(){
        binding.pbMshDetail.setVisibility(View.VISIBLE);
        Call<MetaDataMahasiswaSingle> call = mahasiswaInterface.deleteMahasiswaItem(dataMahasiswa.getNpm());

        call.enqueue(new Callback<MetaDataMahasiswaSingle>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                if(response.isSuccessful()){
                    if(response.body().getMeta().isSuccess()){
                        binding.pbMshDetail.setVisibility(View.GONE);
                        Toast.makeText(MhsDetailActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("NPM", dataMahasiswa.getNpm());
                        intent.putExtra("ACTION", "DELETE");
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }else {
                        binding.pbMshDetail.setVisibility(View.GONE);
                        Toast.makeText(MhsDetailActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    binding.pbMshDetail.setVisibility(View.GONE);
                    Toast.makeText(MhsDetailActivity.this, R.string.error_send_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                t.printStackTrace();
                binding.pbMshDetail.setVisibility(View.GONE);
                Toast.makeText(MhsDetailActivity.this, R.string.error_send_data, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
