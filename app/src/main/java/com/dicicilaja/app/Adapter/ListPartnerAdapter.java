package com.dicicilaja.app.Adapter;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dicicilaja.app.Activity.AllProductActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProductPartner.AllProductPartner;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.dicicilaja.app.R;

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
        final Datum itemModel = partners.get(position);
        Picasso.with(mContext).load(itemModel.getImages()).into(holder.image);
        holder.card_merchant.setOnClickListener(new View.OnClickListener() {
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
        if(partners.size() > 4){
            return 5;
        }else{
            return partners.size();
        }
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected LinearLayout card_merchant;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.card_merchant = itemView.findViewById(R.id.card_merchant);
        }
    }
}
