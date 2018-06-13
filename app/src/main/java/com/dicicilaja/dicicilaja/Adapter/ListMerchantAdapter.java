package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dicicilaja.dicicilaja.Activity.AllProductActivity;
import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.ProductPartnerActivity;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class ListMerchantAdapter extends RecyclerView.Adapter<ListMerchantAdapter.SingleItemRowHolder> {
    private List<Datum> partners;
    private Context mContext;

    public ListMerchantAdapter(List<Datum> partners, Context mContext) {
        this.partners = partners;
        this.mContext = mContext;
    }

    @Override
    public ListMerchantAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_partner, null);
        ListMerchantAdapter.SingleItemRowHolder singleItemRowHolder = new ListMerchantAdapter.SingleItemRowHolder(v);

        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final ListMerchantAdapter.SingleItemRowHolder holder, final int position) {
        final Datum itemModel = partners.get(position);
        Picasso.with(mContext).load(itemModel.getImages()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,AllProductActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());

                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView image;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
        }
    }
}
