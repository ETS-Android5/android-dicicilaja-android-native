package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation.Datum;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ListProductRecommendAdapter extends RecyclerView.Adapter<ListProductRecommendAdapter.SingleItemRowHolder> {
    private List<Datum> recommend;
    private Context mContext;
    Integer id;

    public ListProductRecommendAdapter(List<Datum> recommend, Context mContext) {
        this.recommend = recommend;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);

        tv_title.setTypeface(opensans_bold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, final int position) {
        final Datum itemModel = recommend.get(position);
        holder.tv_title.setText(itemModel.getTitleProgram());
        holder.tv_jenis.setText(itemModel.getJenisProgram());
        holder.tv_harga.setText(itemModel.getPrice());
        holder.tv_mitra.setText(itemModel.getPartner());
        Picasso.get().load(itemModel.getImageUrl()).into(holder.discount_image);
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
        return recommend.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_harga;
        protected TextView tv_jenis;
        protected TextView tv_mitra;
        protected CardView card_rekomendasi;

        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            this.card_rekomendasi = itemView.findViewById(R.id.card_rekomendasi);
        }
    }
}
