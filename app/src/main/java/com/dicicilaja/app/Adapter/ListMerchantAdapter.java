package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dicicilaja.app.Activity.AllProductActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.app.R;

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
        Picasso.get().load(itemModel.getImages()).into(holder.image);
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
