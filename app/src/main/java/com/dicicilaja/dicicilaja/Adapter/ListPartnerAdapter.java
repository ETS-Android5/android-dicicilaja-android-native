package com.dicicilaja.dicicilaja.Adapter;

/**
 * Created by fawazrifqi on 27/02/18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.ProductMaxiActivity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.dicicilaja.dicicilaja.R;

public class ListPartnerAdapter extends RecyclerView.Adapter<ListPartnerAdapter.SingleItemRowHolder> {
    private List<Datum> partners;
    private Context mContext;

    public ListPartnerAdapter(List<Datum> partners, Context mContext) {
        this.partners = partners;
        this.mContext = mContext;
    }

    @Override
    public ListPartnerAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_partner, null);
        ListPartnerAdapter.SingleItemRowHolder singleItemRowHolder = new ListPartnerAdapter.SingleItemRowHolder(v);

        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final ListPartnerAdapter.SingleItemRowHolder holder, final int position) {
        Datum itemModel = partners.get(position);
        Picasso.with(mContext).load(itemModel.getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
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
        return (null != partners ? partners.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView image;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
        }
    }
}