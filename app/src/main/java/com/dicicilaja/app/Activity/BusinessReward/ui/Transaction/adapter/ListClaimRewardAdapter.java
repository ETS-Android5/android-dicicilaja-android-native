package com.dicicilaja.app.Activity.BusinessReward.ui.Transaction.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.API.Model.Transaksi;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum_;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter.ListProductAdapter;
import com.dicicilaja.app.Adapter.TransaksiAdapter;
import com.dicicilaja.app.R;

import java.util.List;

public class ListClaimRewardAdapter extends RecyclerView.Adapter<ListClaimRewardAdapter.MyViewHolder> {

    Context mContext;
    List<Datum> clList;

    public ListClaimRewardAdapter(List<Datum> dataItems, Context baseContext) {
        this.clList = dataItems;
        this.mContext = baseContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_tgl;
        public TextView tv_point;
        public TextView tv_merk;
        public TextView tv_status;
        public CardView card;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_tgl = itemView.findViewById(R.id.tgl);
            this.tv_point = itemView.findViewById(R.id.point);
            this.tv_merk = itemView.findViewById(R.id.merk);
            this.tv_status = itemView.findViewById(R.id.status);
            this.card = itemView.findViewById(R.id.card_trans);
        }
    }

    @NonNull
    @Override
    public ListClaimRewardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaksi, null);
        return new ListClaimRewardAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClaimRewardAdapter.MyViewHolder holder, int position) {
        final Datum cl = clList.get(position);

//        holder.tv_tgl.setText(cl.getAttributes().getTgl());
//        holder.tv_point.setText(String.valueOf(cl.getAttributes().())+" POINT");
//        holder.tv_merk.setText(String.valueOf(transaksi.getMerk()));
//        holder.tv_status.setText(String.valueOf(transaksi.getStatus()));
//        Glide.with(mContext).load(pc.getThumbnail()).into(holder.icon_image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return clList.size();
    }
}
