package com.dicicilaja.app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProduct.Datum;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListProdukAdapter extends RecyclerView.Adapter<ListProdukAdapter.SingleItemRowHolder> {
    private List<Datum> produk;
    private Context mContext;

    public ListProdukAdapter(List<Datum> produk, Context mContext) {
        this.produk = produk;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_all_produk, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_bold);
        return singleItemRowHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        final Datum itemModel = produk.get(position);
        holder.tv_title.setText(itemModel.getTitleProgram());
        if (itemModel.getTitleProgram().length() >= 35) {
            holder.tv_title.setText(itemModel.getTitleProgram().substring(0, 32).trim() + "...");
        }else{
            holder.tv_title.setText(itemModel.getTitleProgram());
        }
        holder.tv_jenis.setText(itemModel.getJenisProgram());
        holder.tv_harga.setText(String.valueOf(itemModel.getPrice()));
        if (itemModel.getMitra().length() >= 23) {
            holder.tv_mitra.setText(itemModel.getMitra().substring(0, 20).trim() + "...");
        }else{
            holder.tv_mitra.setText(itemModel.getMitra());
        }
        Picasso.get().load(itemModel.getImageUrl()).into(holder.discount_image);
        holder.card_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());
                intent.putExtra("DESC",itemModel.getDeskripsi_singkat());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produk.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_jenis;
        protected TextView tv_harga;
        protected TextView tv_mitra;
        protected CardView card_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.card_promo = itemView.findViewById(R.id.card_promo);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
