package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.API.Model.Transaksi;
import com.dicicilaja.app.Activity.DetailProductActivity;
import com.dicicilaja.app.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder>{
    List<Transaksi> transaksiList;
    Context mContext;

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

    public TransaksiAdapter(List<Transaksi> transaksiList, Context mContext) {
        this.transaksiList = transaksiList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TransaksiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaksi, null);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        TextView tgl = v.findViewById(R.id.tgl);
        TextView poin = v.findViewById(R.id.point);
        TextView merk = v.findViewById(R.id.merk);
        TextView status = v.findViewById(R.id.status);
        CardView cv = v.findViewById(R.id.card_trans);

//        tv_title.setTypeface(opensans_reguler);
//        tv_point.setTypeface(opensans_bold);
        return new TransaksiAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.MyViewHolder holder, int position) {
        final Transaksi transaksi = transaksiList.get(position);
        holder.tv_tgl.setText(transaksi.getTgl());
        holder.tv_point.setText(String.valueOf(transaksi.getPoin())+" POINT");
        holder.tv_merk.setText(String.valueOf(transaksi.getMerk()));
        holder.tv_status.setText(String.valueOf(transaksi.getStatus()));
//        Glide.with(mContext).load(pc.getThumbnail()).into(holder.icon_image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, DetailProductActivity.class);
//                intent.putExtra("ID", pc.getId());
//                intent.putExtra("Name", pc.getName());
//                intent.putExtra("Point", pc.getPoint());
//                intent.putExtra("Thumbnail", pc.getThumbnail());
//                view.getContext().startActivity(intent);
//                Snackbar.make(view, "ID : " + pc.getId(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }
}
