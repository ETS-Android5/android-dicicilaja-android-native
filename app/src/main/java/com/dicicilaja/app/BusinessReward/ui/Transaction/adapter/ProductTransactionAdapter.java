package com.dicicilaja.app.BusinessReward.ui.Transaction.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward.Included;
import com.dicicilaja.app.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by Husni with ❤
 */

public class ProductTransactionAdapter extends RecyclerView.Adapter<ProductTransactionAdapter.ProductTransactionViewHolder> {

    private Context mContext;
    private List<Included> includedList;

    public ProductTransactionAdapter(Context mContext, List<Included> includedList) {
        this.mContext = mContext;
        this.includedList = includedList;
    }

    @NonNull
    @Override
    public ProductTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_product_history, parent, false);
        return new ProductTransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductTransactionViewHolder holder, int position) {
        holder.bind(includedList.get(position));
    }

    @Override
    public int getItemCount() {
        return includedList.size();
    }

    class ProductTransactionViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProduct;
        private TextView tvProductName;
        private TextView tvPoint;

        public ProductTransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct = itemView.findViewById(R.id.barang_picture);
            tvProductName = itemView.findViewById(R.id.title_barang);
            tvPoint = itemView.findViewById(R.id.point);
        }

        public void bind(Included data) {
            if (data.getAttributes() != null && data.getAttributes().getFoto() != null && !data.getAttributes().getFoto().isEmpty()) {
                Glide.with(mContext)
                        .load(data.getAttributes().getFoto())
                        .centerCrop()
                        .fitCenter()
                        .into(ivProduct);
            }

            if (data.getAttributes().getNama() != null) {
                tvProductName.setText(data.getAttributes().getNama());
            }

            if (data.getAttributes().getPoint() > -1) {
                String point = String.format(new Locale("in", "ID"), "%d POINT", data.getAttributes().getPoint());
                tvPoint.setText(point);
            }
        }
    }
}
