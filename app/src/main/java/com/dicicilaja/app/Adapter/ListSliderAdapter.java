package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.app.Activity.PromoActivity;
import com.dicicilaja.app.R;

/**
 * Created by fawazrifqi on 16/04/18.
 */

public class ListSliderAdapter extends RecyclerView.Adapter<ListSliderAdapter.SingleItemRowHolder> {
    private List<com.dicicilaja.app.API.Item.Slider.Datum> sliders;
    private Context mContext;

    public ListSliderAdapter(List<com.dicicilaja.app.API.Item.Slider.Datum> sliders, Context mContext) {
        this.sliders = sliders;
        this.mContext = mContext;
    }

    @Override
    public ListSliderAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_promo_list, null);
        ListSliderAdapter.SingleItemRowHolder singleItemRowHolder = new ListSliderAdapter.SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_semibold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(ListSliderAdapter.SingleItemRowHolder holder, final int position) {
        final com.dicicilaja.app.API.Item.Slider.Datum itemModel = sliders.get(position);

        holder.tv_title.setText(itemModel.getName());
        holder.tv_date.setText(itemModel.getName());
        Picasso.with(mContext).load(itemModel.getImageUrl()).into(holder.discount_image);
        holder.card_list_promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PromoActivity.class);
                intent.putExtra("ID", itemModel.getId().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != sliders ? sliders.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_date;
        protected LinearLayout card_list_promo;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_date = itemView.findViewById(R.id.tv_date);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            this.card_list_promo = itemView.findViewById(R.id.card_list_promo);
        }
    }
}
