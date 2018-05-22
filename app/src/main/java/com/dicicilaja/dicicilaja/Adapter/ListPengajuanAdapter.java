package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemRequestMarketplace.Datum;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by fawazrifqi on 16/05/18.
 */

public class ListPengajuanAdapter extends RecyclerView.Adapter<ListPengajuanAdapter.SingleItemRowHolder> {
    private List<Datum> requests;
    private Context mContext;

    public ListPengajuanAdapter(List<Datum> requests, Context mContext) {
        this.requests = requests;
        this.mContext = mContext;
    }

    @Override
    public ListPengajuanAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pengajuan_marketplace, null);
        ListPengajuanAdapter.SingleItemRowHolder singleItemRowHolder = new ListPengajuanAdapter.SingleItemRowHolder(v);

        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final ListPengajuanAdapter.SingleItemRowHolder holder, final int position) {
        Datum itemModel = requests.get(position);
        holder.tv_title.setText("WONDER-FULL LAND TOUR KOMODO");
        holder.tv_mitra.setText("TX Travel");
        holder.tv_harga.setText("Rp 4,086,000");
        Picasso.with(mContext).load("http").into(holder.image);
        holder.card_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"ID : " + position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("ID", position);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected CardView card_pengajuan;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.image = itemView.findViewById(R.id.image);
            this.card_pengajuan = itemView.findViewById(R.id.card_pengajuan);
        }
    }
}

