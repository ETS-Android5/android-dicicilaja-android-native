package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.Activity.ProductActivity;
import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.Content.RekomendasiModel;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 2/17/2018.
 */

public class ListRekomendasiAdapter extends RecyclerView.Adapter<ListRekomendasiAdapter.SingleItemRowHolder> {
    private List<id.variable.dicicilaja.API.Item.Recommend.Datum> recommends;
    private Context mContext;

    public ListRekomendasiAdapter(List<id.variable.dicicilaja.API.Item.Recommend.Datum> recommends, Context mContext) {
        this.recommends = recommends;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rekomendasi, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);

        tv_title.setTypeface(opensans_semibold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, final int position) {
        id.variable.dicicilaja.API.Item.Recommend.Datum itemModel = recommends.get(position);
        holder.tv_title.setText(itemModel.getName());
        holder.tv_mitra.setText(itemModel.getMerchant().getCompany());
        holder.tv_harga.setText(itemModel.getPrice());
        Picasso.with(mContext).load(itemModel.getImage()).into(holder.discount_image);
        holder.tv_tenor.setText(itemModel.getExcerpt());
        holder.card_rekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"ID : " + position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ProductActivity.class);
                intent.putExtra("ID", position);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != recommends ? recommends.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_tenor;
        protected CardView card_rekomendasi;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_tenor = itemView.findViewById(R.id.tv_tenor);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            this.card_rekomendasi = itemView.findViewById(R.id.card_rekomendasi);
        }
    }
}
