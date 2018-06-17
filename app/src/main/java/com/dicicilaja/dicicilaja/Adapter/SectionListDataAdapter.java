package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.dicicilaja.dicicilaja.API.Item.Product.SingleItemModel;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {
    private ArrayList<SingleItemModel> itemModels;
    private Context mContext;

    public SectionListDataAdapter(ArrayList<SingleItemModel> itemModels, Context mContext) {
        this.itemModels = itemModels;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rekomendasi, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        SingleItemModel itemModel = itemModels.get(position);
        holder.tv_title.setText(itemModel.getTv_title());
        Picasso.with(mContext).load(itemModel.getImage()).into(holder.image);
        holder.tv_mitra.setText(itemModel.getTv_mitra());
        holder.tv_harga.setText(itemModel.getTv_harga());
        holder.tv_tenor.setText(itemModel.getTv_tenor());
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_tenor;

        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.image = itemView.findViewById(R.id.image);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_tenor = itemView.findViewById(R.id.tv_tenor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), tv_title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
