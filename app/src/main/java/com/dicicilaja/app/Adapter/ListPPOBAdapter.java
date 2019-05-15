package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Model.LayananPPOB.PPOB;
import com.dicicilaja.app.Activity.ProductMaxiActivity;
import com.dicicilaja.app.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPPOBAdapter extends RecyclerView.Adapter<ListPPOBAdapter.MyViewHolder> {
    private List<PPOB> ppobList;
    public Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public ImageView icon_image;
        public CardView card_ppob;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.card_ppob = itemView.findViewById(R.id.card_ppob);
            this.icon_image = itemView.findViewById(R.id.icon_image);
        }
    }

    public ListPPOBAdapter(List<PPOB> ppobList, Context mContext) {
        this.ppobList = ppobList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ppob, null);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_bold);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PPOB ppob = ppobList.get(position);
        holder.tv_title.setText(ppob.getName());
        Glide.with(mContext).load(R.drawable.ic_home).into(holder.icon_image);
        holder.card_ppob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ProductMaxiActivity.class);
//                intent.putExtra("ID", ppob.getId());
//                view.getContext().startActivity(intent);
                Snackbar.make(view, "ID : " + ppob.getId(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ppobList.size();
    }
}