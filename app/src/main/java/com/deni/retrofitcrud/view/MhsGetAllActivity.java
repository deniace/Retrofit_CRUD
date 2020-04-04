package com.deni.retrofitcrud.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.RetrofitInstance;
import com.deni.retrofitcrud.adapter.MhsGetAllAdapter;
import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMhsGetAllBinding;
import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Deni Supriyatna on 12 - Mar - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class MhsGetAllActivity extends AppCompatActivity {

    private ActivityMhsGetAllBinding binding;
    private MahasiswaInterface mahasiswaInterface;
    private LinearLayoutManager linearLayoutManager;
    private MhsGetAllAdapter adapter;
    private List<DataMahasiswa> list;
    private String TAG = "MhsGetAllActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_mhs_get_all);
        binding.setActivity(this);
        mahasiswaInterface = RetrofitInstance.getRetrofit(this).create(MahasiswaInterface.class);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.rvMhsGetAll.setLayoutManager(linearLayoutManager);
        binding.rvMhsGetAll.setHasFixedSize(true);
        binding.srlMhsGetAll.setOnRefreshListener(refreshListener);
        adapter = new MhsGetAllAdapter(listener);
        binding.rvMhsGetAll.setAdapter(adapter);
        loadData();
    }

    private MhsGetAllAdapter.OnItemClickListener listener = new MhsGetAllAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(DataMahasiswa item) {
            Toast.makeText(MhsGetAllActivity.this, item.getNpm(), Toast.LENGTH_SHORT).show();
        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.rvMhsGetAll.removeOnScrollListener(scrollListener);
            loadData();
        }
    };

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    private void loadData(){
        binding.srlMhsGetAll.setRefreshing(true);
        adapter = new MhsGetAllAdapter(listener);
        binding.rvMhsGetAll.setAdapter(adapter);

        Call<MetaDataMahasiswa>call = mahasiswaInterface.getMahasiswa();

        call.enqueue(new Callback<MetaDataMahasiswa>() {
            @Override
            public void onResponse(Call<MetaDataMahasiswa> call, Response<MetaDataMahasiswa> response) {
                if(response.isSuccessful()){
                    if (response.body().getMeta().isSuccess()){
                        binding.srlMhsGetAll.setRefreshing(false);
                        list = response.body().getDataMahasiswa();
                        Log.d(TAG, "onResponse: nama mahasiswa = "+list.get(0).getNamaMahasiswa());
                        adapter.addItems(list);
                        adapter.notifyDataSetChanged();
                    }else {
                        binding.srlMhsGetAll.setRefreshing(false);
                        Toast.makeText(MhsGetAllActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    binding.srlMhsGetAll.setRefreshing(false);
                    Toast.makeText(MhsGetAllActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MetaDataMahasiswa> call, Throwable t) {
                t.printStackTrace();
                binding.srlMhsGetAll.setRefreshing(false);
                Toast.makeText(MhsGetAllActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
