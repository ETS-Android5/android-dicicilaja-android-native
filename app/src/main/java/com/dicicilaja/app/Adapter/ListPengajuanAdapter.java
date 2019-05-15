package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.app.Activity.DetailRequestActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllPengajuan.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.app.R;

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
        final Datum itemModel = requests.get(position);
        holder.tv_title.setText(itemModel.getTitleProgram());
        holder.tv_mitra.setText(itemModel.getPartner());
        holder.tv_harga.setText(itemModel.getPrice());
        Picasso.get().load(itemModel.getImageUrl()).into(holder.image);
        if(itemModel.getStatus().equals("Terkirim")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if(itemModel.getStatus().equals("Konfirmasi")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.konfirmasi_active).into(holder.img_2);
            holder.txt_2.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if(itemModel.getStatus().equals("Proses")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.konfirmasi_active).into(holder.img_2);
            holder.txt_2.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.proses_active).into(holder.img_3);
            holder.txt_3.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if(itemModel.getStatus().equals("Survey")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.konfirmasi_active).into(holder.img_2);
            holder.txt_2.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.proses_active).into(holder.img_3);
            holder.txt_3.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.survey_active).into(holder.img_4);
            holder.txt_4.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if(itemModel.getStatus().equals("Analisa Kredit") || itemModel.getStatus().equals("Ditolak") || itemModel.getStatus().equals("Pending")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.konfirmasi_active).into(holder.img_2);
            holder.txt_2.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.proses_active).into(holder.img_3);
            holder.txt_3.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.survey_active).into(holder.img_4);
            holder.txt_4.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.penilaian_active).into(holder.img_5);
            holder.txt_5.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else if(itemModel.getStatus().equals("Pencairan")) {
            Picasso.get().load(R.drawable.terkirim_active).into(holder.img_1);
            holder.txt_1.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.konfirmasi_active).into(holder.img_2);
            holder.txt_2.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.proses_active).into(holder.img_3);
            holder.txt_3.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.survey_active).into(holder.img_4);
            holder.txt_4.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.penilaian_active).into(holder.img_5);
            holder.txt_5.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            Picasso.get().load(R.drawable.pencairan_active).into(holder.img_6);
            holder.txt_6.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        holder.card_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailRequestActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected CardView card_pengajuan;

        protected ImageView img_1;
        protected TextView txt_1;
        protected ImageView img_2;
        protected TextView txt_2;
        protected ImageView img_3;
        protected TextView txt_3;
        protected ImageView img_4;
        protected TextView txt_4;
        protected ImageView img_5;
        protected TextView txt_5;
        protected ImageView img_6;
        protected TextView txt_6;


        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.image = itemView.findViewById(R.id.image);
            this.img_1 = itemView.findViewById(R.id.img_1);
            this.txt_1 = itemView.findViewById(R.id.txt_1);
            this.img_2 = itemView.findViewById(R.id.img_2);
            this.txt_2 = itemView.findViewById(R.id.txt_2);
            this.img_3 = itemView.findViewById(R.id.img_3);
            this.txt_3 = itemView.findViewById(R.id.txt_3);
            this.img_4 = itemView.findViewById(R.id.img_4);
            this.txt_4 = itemView.findViewById(R.id.txt_4);
            this.img_5 = itemView.findViewById(R.id.img_5);
            this.txt_5 = itemView.findViewById(R.id.txt_5);
            this.img_6 = itemView.findViewById(R.id.img_6);
            this.txt_6 = itemView.findViewById(R.id.txt_6);
            this.card_pengajuan = itemView.findViewById(R.id.card_pengajuan);
        }
    }
}

