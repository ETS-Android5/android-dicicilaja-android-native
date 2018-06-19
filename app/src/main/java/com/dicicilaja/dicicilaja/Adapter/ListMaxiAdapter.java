package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProduct.Datum;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiProgram.Data;
import com.dicicilaja.dicicilaja.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMaxiAdapter extends RecyclerView.Adapter<ListMaxiAdapter.SingleItemRowHolder> {
    private List<Data> produk;
    private Context mContext;

    public ListMaxiAdapter(List<Data> produk, Context mContext) {
        this.produk = produk;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_bold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        final Data itemModel = produk.get(position);
        holder.tv_title.setText(itemModel.getName());
        holder.tv_jenis.setText(itemModel.getPartner());
        holder.tv_harga.setText(String.valueOf(itemModel.getPrice()));
        Picasso.with(mContext).load(itemModel.getImage()).into(holder.discount_image);
        holder.card_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());
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
        protected CardView card_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.card_promo = itemView.findViewById(R.id.card_promo);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
