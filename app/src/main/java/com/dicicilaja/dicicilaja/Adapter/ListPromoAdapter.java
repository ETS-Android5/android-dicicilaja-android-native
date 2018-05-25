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

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by ziterz on 2/17/2018.
 */

public class ListPromoAdapter extends RecyclerView.Adapter<ListPromoAdapter.SingleItemRowHolder> {
    private List<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos;
    private Context mContext;

    public ListPromoAdapter(List<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos, Context mContext) {
        this.promos = promos;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_promo, null);
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
        final com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum itemModel = promos.get(position);
        holder.tv_title.setText(itemModel.getName());
        holder.tv_mitra.setText(itemModel.getPartner());
        holder.tv_harga.setText(itemModel.getPrice());
        Picasso.with(mContext).load(itemModel.getImage()).into(holder.discount_image);
        holder.tv_tenor.setText(itemModel.getExcerpt());
        holder.card_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"ID : " + itemModel.getId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("ID", itemModel.getId().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != promos ? promos.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_tenor;
        protected CardView card_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_tenor = itemView.findViewById(R.id.tv_tenor);
            this.card_promo = itemView.findViewById(R.id.card_promo);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
