package com.dicicilaja.app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.Data;
import com.dicicilaja.app.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jsoup.Jsoup;

import java.util.List;

public class ListMaxiAdapter extends RecyclerView.Adapter<ListMaxiAdapter.SingleItemRowHolder> {
    private List<Data> produk;
    private Context mContext;

    public ListMaxiAdapter(List<Data> produk, Context mContext) {
        this.produk = produk;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_bold);
        return singleItemRowHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        final Data itemModel = produk.get(position);
        holder.tv_title.setText(itemModel.getName());
        if (itemModel.getExcerpt().length() >= 500) {
            holder.tv_jenis.setText(Jsoup.parse(itemModel.getExcerpt()).text().substring(0, 497).trim() + "...");
        }else{
            holder.tv_jenis.setText(Jsoup.parse(itemModel.getExcerpt()).text());
        }
        holder.tv_mitra.setText(itemModel.getPartner());
        holder.tv_harga.setText(String.valueOf(itemModel.getPrice()));
        //Picasso.with(mContext).load(itemModel.getImage()).into(holder.discount_image);
        Uri uri = Uri.parse(itemModel.getImage());
        holder.discount_image.setImageURI(uri);
        holder.card_promo.setOnClickListener(new View.OnClickListener() {
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
        return produk.size();
    }

    public String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected SimpleDraweeView discount_image;
        protected TextView tv_jenis;
        protected TextView tv_harga;
        protected TextView tv_mitra;
        protected CardView card_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_jenis = itemView.findViewById(R.id.tv_jenis);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.card_promo = itemView.findViewById(R.id.card_promo);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
