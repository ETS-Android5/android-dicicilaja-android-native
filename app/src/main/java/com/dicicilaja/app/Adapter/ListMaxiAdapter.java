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
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.Tenor;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ListMaxiAdapter extends RecyclerView.Adapter<ListMaxiAdapter.SingleItemRowHolder> {
    private List<Datum> produk;
    private List<Tenor> tenor;
    private StringBuilder sb = new StringBuilder();
    private String cicilan;
    private Context mContext;

    public ListMaxiAdapter(List<Datum> produk, Context mContext) {
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
        final Datum itemModel = produk.get(position);
        sb = new StringBuilder();
        holder.tv_title.setText(itemModel.getName());
        if (itemModel.getName().length() >= 35) {
            holder.tv_title.setText(itemModel.getName().substring(0, 32).trim() + "...");
        }else{
            holder.tv_title.setText(itemModel.getName());
        }
//        if (itemModel.getExcerpt().length() >= 150) {
//            holder.tv_jenis.setText(Jsoup.parse(itemModel.getExcerpt()).text().substring(0, 147).trim() + "...");
//        }else{
//            holder.tv_jenis.setText(Jsoup.parse(itemModel.getExcerpt()).text());
//        }
        holder.tv_mitra.setText(itemModel.getPartner());
        holder.tv_harga.setText(String.valueOf(itemModel.getPrice()));
        for(int i = 0 ; i<itemModel.getTenor().size();i++){
            if (i == 0) {
                sb.append(itemModel.getTenor().get(0).get(i).getCicilan()).toString();
            }else{
                sb.append("\n"+itemModel.getTenor().get(0).get(i).getCicilan()).toString();
            }
        }
        cicilan = sb.toString();
//        holder.tv_cicilan.setText(cicilan);
        Uri uri = Uri.parse(itemModel.getImage());
        Picasso.get().load(uri).placeholder(R.drawable.illustration_1_05).into(holder.discount_image);
        holder.card_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ProductMaxiActivity.class);
                intent.putExtra("EXTRA_REQUEST_ID", itemModel.getId().toString());
                intent.putExtra("DESC", itemModel.getExcerpt());
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
        protected ImageView discount_image;
        protected TextView tv_harga;
        protected TextView tv_mitra;
//        protected TextView tv_cicilan;
        protected CardView card_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
//            this.tv_cicilan = itemView.findViewById(R.id.tv_cicilan);
            this.card_promo = itemView.findViewById(R.id.card_promo);
            this.discount_image = itemView.findViewById(R.id.discount_image);
        }
    }
}
