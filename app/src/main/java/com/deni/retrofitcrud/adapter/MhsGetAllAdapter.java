package com.deni.retrofitcrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.deni.retrofitcrud.R;
import com.deni.retrofitcrud.databinding.MhsGetAllItemLayoutBinding;
import com.deni.retrofitcrud.model.DataMahasiswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deni Supriyatna (deni ace) on 12 - Mar - 2020.
 * Email : denisupriyatna01@gmail.com
 */
public class MhsGetAllAdapter extends RecyclerView.Adapter<MhsGetAllAdapter.MhsGetAllViewHolder>{
    private List<DataMahasiswa> list;
    private List<DataMahasiswa> listFull;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        public void onItemClick(DataMahasiswa item);
    }

    public class MhsGetAllViewHolder extends RecyclerView.ViewHolder{
        private MhsGetAllItemLayoutBinding binding;

        public MhsGetAllViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public MhsGetAllViewHolder(@NonNull MhsGetAllItemLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final DataMahasiswa item, final OnItemClickListener listener){
            binding.setMhsdata(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(item);
                    }
                }
            });
        }
    }

    public MhsGetAllAdapter(){
        list = new ArrayList<>();
        listFull = new ArrayList<>();
    }

    public MhsGetAllAdapter(@NonNull List<DataMahasiswa> list){
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    public MhsGetAllAdapter(@NonNull OnItemClickListener listener){
        list = new ArrayList<>();
        listFull = new ArrayList<>(list);
        this.listener = listener;
    }

    public MhsGetAllAdapter(List<DataMahasiswa> list, OnItemClickListener listener){
        this.list = list;
        listFull = new ArrayList<>(list);
        this.listener = listener;
    }

    public List<DataMahasiswa> getList(){
        return list;
    }

    public List<DataMahasiswa> getListFull(){
        return listFull;
    }

    public void addItems(List<DataMahasiswa> items){
        list.addAll(items);
        listFull.addAll(items);
    }

    @NonNull
    @Override
    public MhsGetAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MhsGetAllItemLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.mhs_get_all_item_layout, parent, false);
        MhsGetAllViewHolder holder = new MhsGetAllViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MhsGetAllViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
