package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.Related;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation.Datum;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListRelatedAdapter extends RecyclerView.Adapter<ListRelatedAdapter.SingleItemRowHolder> {
    private List<Related> recommends;
    private Context mContext;
    Integer id;

    public ListRelatedAdapter(List<Related> recommends, Context mContext) {
        this.recommends = recommends;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rekomendasi, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);

        tv_title.setTypeface(opensans_bold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, final int position) {
        final Related itemModel = recommends.get(position);
        holder.tv_title.setText(itemModel.getTitleProgram());
        holder.tv_mitra.setText(itemModel.getPartner());
        holder.tv_jenis.setText(itemModel.getJenisProgram());
        holder.tv_harga.setText(itemModel.getPrice());
        Picasso.with(mContext).load(itemModel.getImageUrl()).into(holder.discount_image);
        holder.card_rekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(recommends.size() > 4){
            return 5;
        }else{
            return recommends.size();
        }
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_jenis;
        protected CardView card_rekomendasi;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            this.card_rekomendasi = itemView.findViewById(R.id.card_rekomendasi);
        }
    }
}
