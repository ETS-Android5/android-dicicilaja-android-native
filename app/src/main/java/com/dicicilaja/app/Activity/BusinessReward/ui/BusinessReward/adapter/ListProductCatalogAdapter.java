package com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Attributes;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.activity.CatalogResultActivity;
import com.dicicilaja.app.Activity.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListProductCatalogAdapter extends RecyclerView.Adapter<ListProductCatalogAdapter.MyViewHolder> {
    List<com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum> pcList;
    List<com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included> pcList2;
    ListProductAdapter horizontalAdapter;
    Context mContext;
    public String nama;
    int no = 0;

    public ListProductCatalogAdapter(List<Datum> dataItems, List<Included> dataItems2, Context baseContext) {
        this.pcList = dataItems;
        this.pcList2 = dataItems2;
        this.mContext = baseContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView lihatSemua;
        public RecyclerView rvProduk;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.title_kategori);
            this.rvProduk = itemView.findViewById(R.id.recycler_produks);
            this.lihatSemua = itemView.findViewById(R.id.lihat_semua);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_title, null);
        TextView tv_title = v.findViewById(R.id.title_kategori);
        return new ListProductCatalogAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Datum pc = pcList.get(position);

//        final Included pc2 = pcList2.get(position);

        pc.getRelationships().getProductCatalogs().getData().size();

        holder.tv_title.setText(pc.getAttributes().getNama());
        Log.d("Relasi", " id: " + pc.getRelationships());

//        for (int i = 0; i < pc.getRelationships().getProductCatalogs().getData().size(); i++){
//        Log.d("idnyaanjing", String.valueOf(pc.getRelationships().getProductCatalogs().getData().get(position).getId()));
        horizontalAdapter = new ListProductAdapter(pc.getRelationships().getProductCatalogs().getData(), pcList2, mContext);
        holder.rvProduk.setHasFixedSize(true);
        holder.rvProduk.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rvProduk.setAdapter(horizontalAdapter);
//        }
        no++;
//        horizontalAdapter = new ListProductAdapter(pc.getRelationships().getProductCatalogs().getData(), pcList2, mContext);

        holder.lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CatalogResultActivity.class);
                intent.putExtra("ID", String.valueOf(pc.getId()));
                intent.putExtra("SIZE", String.valueOf(pc.getRelationships().getProductCatalogs().getData().size()));
                v.getContext().startActivity(intent);
            }
        });

        SnapHelper snapHelperMotor = new GravitySnapHelper(Gravity.START);
        snapHelperMotor.attachToRecyclerView(holder.rvProduk);
    }

    @Override
    public int getItemCount() {
        return pcList.size();
    }

}
