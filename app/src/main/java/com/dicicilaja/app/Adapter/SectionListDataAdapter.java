package com.dicicilaja.app.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.dicicilaja.app.API.Model.Product.SingleItemModel;
import com.dicicilaja.app.R;

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
        Picasso.get().load(itemModel.getImage()).into(holder.discount_image);
        holder.tv_mitra.setText(itemModel.getTv_mitra());
        holder.tv_harga.setText(itemModel.getTv_harga());
        holder.tv_jenis.setText(itemModel.getTv_tenor());
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_jenis;

        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), tv_title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
