package com.deni.retrofitcrud.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.RetrofitInstance;
import com.deni.retrofitcrud.adapter.MhsGetAllAdapter;
import com.deni.retrofitcrud.apiinterface.MahasiswaInterface;
import com.deni.retrofitcrud.databinding.ActivityMhsGetAllBinding;
import com.deni.retrofitcrud.model.DataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswa;
import com.deni.retrofitcrud.model.MetaDataMahasiswaSingle;
import com.deni.retrofitcrud.model.MetaDataPaging;
import com.deni.retrofitcrud.model.PagingPost;

import java.io.IOException;
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
    private int page = 0;
    private int page_size = 5;
    private int totalData = 0;
    private boolean isFistView = true;
    private MhsGetAllAdapter adapter;
//    private List<DataMahasiswa> list;
    private String TAG = "MhsGetAllActivity";
    private int itemIndex = -1;
    private int itemIndexFull = -1;

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
        loadData(false);
    }

    private MhsGetAllAdapter.OnItemClickListener listener = new MhsGetAllAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(DataMahasiswa item) {
            itemIndex = adapter.getList().indexOf(item);
            itemIndexFull = adapter.getListFull().indexOf(item);
            Intent intent = new Intent(MhsGetAllActivity.this, MhsDetailActivity.class);
            intent.putExtra("STATE", 1);
            intent.putExtra("NPM", item.getNpm());
            startActivityForResult(intent, 1);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.rvMhsGetAll.removeOnScrollListener(scrollListener);
            loadData(false);
        }
    };

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int totalItemCount = adapter.getList().size();
            int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            boolean isLastItemVisible = lastItemPosition == totalItemCount -1;
            if (totalItemCount > 0 && isLastItemVisible){
                recyclerView.removeOnScrollListener(this);
                loadData(true);
                Log.d(TAG, "onScrolled: last item position = "+lastItemPosition);
            }
        }
    };

    private void loadData(boolean isNext){
        binding.srlMhsGetAll.setRefreshing(true);
        if (isNext){
            page++;
        }else{
            adapter = new MhsGetAllAdapter(listener);
            binding.rvMhsGetAll.setAdapter(adapter);
            page = 1;
        }

        Call<MetaDataPaging>call = mahasiswaInterface.getPagingMahasiswa(getPage());

        call.enqueue(new Callback<MetaDataPaging>() {
            @Override
            public void onResponse(Call<MetaDataPaging> call, Response<MetaDataPaging> response) {
                if(response.isSuccessful()){
                    if (response.body().getMeta().isSuccess()){
                        binding.srlMhsGetAll.setRefreshing(false);
                        Log.d(TAG, "onResponse: nama mahasiswa = "+response.body().getDataPaging().getRowPaging().get(0).getNamaMahasiswa());
                        adapter.addItems(response.body().getDataPaging().getRowPaging());
                        adapter.notifyItemRangeInserted(adapter.getItemCount(), response.body().getDataPaging().getRowPaging().size());

                        if (adapter.getListFull().size() < response.body().getDataPaging().getTotalData()){
                            binding.rvMhsGetAll.addOnScrollListener(scrollListener);
                        }
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
            public void onFailure(Call<MetaDataPaging> call, Throwable t) {
                t.printStackTrace();
                binding.srlMhsGetAll.setRefreshing(false);
                Toast.makeText(MhsGetAllActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PagingPost getPage(){
        PagingPost pagingPost = new PagingPost();
        pagingPost.setPage(page);
        pagingPost.setPageSize(page_size);
        return pagingPost;
    }

    /**
     * untuk pindah ke tambah data
     * @param view = fab_tambah_mhs
     */
    public void addNew(View view){
        Intent intent = new Intent(MhsGetAllActivity.this, MhsDetailActivity.class);
        intent.putExtra("STATE", 0);
        intent.putExtra("NPM", "");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: result code = "+resultCode+" requestCode = "+requestCode);
        if (resultCode == RESULT_OK){
            String npm = data.getStringExtra("NPM");
            String action = data.getStringExtra("ACTION");
            Log.d(TAG, "onActivityResult: NPM ="+npm);

            if(action.equals("DELETE")){
                adapter.getList().remove(itemIndex);
                adapter.getListFull().remove(itemIndexFull);
                adapter.notifyDataSetChanged();
            }else {
                binding.srlMhsGetAll.setRefreshing(true);
                Call<MetaDataMahasiswaSingle> call = mahasiswaInterface.getMahasiswaItem(npm);

                call.enqueue(new Callback<MetaDataMahasiswaSingle>() {
                    @Override
                    public void onResponse(Call<MetaDataMahasiswaSingle> call, Response<MetaDataMahasiswaSingle> response) {
                        if(response.isSuccessful()){
                            if (response.body().getMeta().isSuccess()){
                                binding.srlMhsGetAll.setRefreshing(false);
                                DataMahasiswa data = response.body().getDataMahasiswa();

                                if(action.equals("NEW")){
                                    adapter.getList().add(data);
                                    adapter.getListFull().add(data);
                                }else{
                                    adapter.getList().set(itemIndex, data);
                                    adapter.getListFull().set(itemIndexFull,data);
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                binding.srlMhsGetAll.setRefreshing(false);
                                Toast.makeText(MhsGetAllActivity.this, response.body().getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            binding.srlMhsGetAll.setRefreshing(false);
                            Toast.makeText(MhsGetAllActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MetaDataMahasiswaSingle> call, Throwable t) {
                        binding.srlMhsGetAll.setRefreshing(false);
                        t.printStackTrace();
                        Toast.makeText(MhsGetAllActivity.this, R.string.error_get_data, Toast.LENGTH_SHORT).show();
                        if(t instanceof IOException){
                            Toast.makeText(MhsGetAllActivity.this, "Network Failure", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MhsGetAllActivity.this, "Big Problem", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}
