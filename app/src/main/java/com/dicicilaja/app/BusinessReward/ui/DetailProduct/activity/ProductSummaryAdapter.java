package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Item;
import com.dicicilaja.app.R;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class ProductSummaryAdapter extends RecyclerView.Adapter<ProductSummaryAdapter.ProductViewHolder> {

    private List<Item> itemList;
    private List<Included> includedList;
    private Context mContext;

    public ProductSummaryAdapter(List<Item> itemList, List<Included> includedList, Context mContext) {
        this.itemList = itemList;
        this.includedList = includedList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_product_summary, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(includedList.get(position));
    }

    @Override
    public int getItemCount() {
        return includedList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName;
        TextView tvProductPoint;
        TextView tvQuantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduct = itemView.findViewById(R.id.barang_picture);
            tvProductName = itemView.findViewById(R.id.title_barang);
            tvProductPoint = itemView.findViewById(R.id.point);
            tvQuantity = itemView.findViewById(R.id.quantity);
        }

        public void bind(Included included) {
            Glide.with(mContext)
                    .load(included.getAttributes().getFoto())
                    .centerCrop()
                    .into(ivProduct);

            String value_title = included.getAttributes().getNama();
            if (value_title.length() >= 35) {
                value_title = value_title.substring(0, 36) + "...";
                tvProductName.setText(value_title);
            } else {
                tvProductName.setText(value_title);
            }
            tvProductPoint.setText(included.getAttributes().getPoint() + " POINT");
            tvQuantity.setText( "x" + itemList.get(getAdapterPosition()).getCounts());
        }
    }
}
