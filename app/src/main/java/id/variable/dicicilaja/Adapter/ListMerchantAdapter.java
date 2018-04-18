package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.Activity.ProductActivity;
import id.variable.dicicilaja.Activity.ProductPartnerActivity;
import id.variable.dicicilaja.R;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class ListMerchantAdapter extends RecyclerView.Adapter<ListMerchantAdapter.SingleItemRowHolder> {
    private List<id.variable.dicicilaja.API.Item.Mechant.Datum> merchants;
    private Context mContext;

    public ListMerchantAdapter(List<id.variable.dicicilaja.API.Item.Mechant.Datum> merchants, Context mContext) {
        this.merchants = merchants;
        this.mContext = mContext;
    }

    @Override
    public ListMerchantAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_partner, null);
        ListMerchantAdapter.SingleItemRowHolder singleItemRowHolder = new ListMerchantAdapter.SingleItemRowHolder(v);

        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(ListMerchantAdapter.SingleItemRowHolder holder, final int position) {
        final id.variable.dicicilaja.API.Item.Mechant.Datum itemModel = merchants.get(position);
        Picasso.with(mContext).load(itemModel.getLogo()).into(holder.discount_image);
        holder.card_merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"ID : " + itemModel.getId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ProductPartnerActivity.class);
                intent.putExtra("ID", itemModel.getId().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != merchants ? merchants.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView discount_image;
        protected LinearLayout card_merchant;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.card_merchant = itemView.findViewById(R.id.card_merchant);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
