package com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.R;

import java.util.List;

public class ListAllProductAdapter extends RecyclerView.Adapter<ListAllProductAdapter.MyViewHolder> {
    Data pcAll;
    List<Included> pcAll2;
    Context mContext;
    String nama, id, size;
    int no = 0;

    public ListAllProductAdapter(Data dataItems, List<Included> dataItems2, Context baseContext, String id, String size) {
        this.mContext = baseContext;
        this.pcAll = dataItems;
        this.pcAll2 = dataItems2;
        this.id = id;
        this.size = size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_point;
        public CardView card_pc;
        public ImageView iconImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.card_pc = itemView.findViewById(R.id.card_pc);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_point = itemView.findViewById(R.id.tv_point);
            this.iconImage = itemView.findViewById(R.id.icon_image);
        }
    }

    @NonNull
    @Override
    public ListAllProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_product_catalog, null);
        return new ListAllProductAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Log.d("IDNYADANID", id + pcAll.get(position).getRelationships().getKategori().getData().getId());
            if (pcAll2.get(position).getAttributes().getNama().length() >= 30) {
                nama = pcAll2.get(position).getAttributes().getNama().substring(0, 29) + "...";
            } else {
                nama = pcAll2.get(position).getAttributes().getNama();
            }
            String title = nama;
            holder.tv_title.setText(title);
            holder.tv_point.setText(String.valueOf(pcAll2.get(position).getAttributes().getPoint()));
            Glide.with(mContext).load(pcAll2.get(position).getAttributes().getFoto()).into(holder.iconImage);
            int finalpos = position;
            holder.card_pc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailProductActivity.class);
                    intent.putExtra("ID", pcAll2.get(finalpos).getId());
                    view.getContext().startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return pcAll2.size();
    }
}
