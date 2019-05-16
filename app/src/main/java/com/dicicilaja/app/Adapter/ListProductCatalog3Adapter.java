package com.dicicilaja.app.Adapter;

import android.content.Context;
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
import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ListProductCatalog3Adapter extends RecyclerView.Adapter<ListProductCatalog3Adapter.MyViewHolder> {
    List<ProductCatalog> pcList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_point;
        public ImageView icon_image;
        public CardView card_pc;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_point = itemView.findViewById(R.id.tv_point);
            this.card_pc = itemView.findViewById(R.id.card_pc);
            this.icon_image = itemView.findViewById(R.id.icon_image);
        }
    }

    public ListProductCatalog3Adapter(List<ProductCatalog> pcList, Context mContext) {
        this.pcList = pcList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ListProductCatalog3Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_catalog, null);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        TextView tv_point = v.findViewById(R.id.tv_point);
        tv_title.setTypeface(opensans_reguler);
        tv_point.setTypeface(opensans_bold);
        return new ListProductCatalog3Adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductCatalog3Adapter.MyViewHolder holder, int position) {
        final ProductCatalog pc = pcList.get(position);
        holder.tv_title.setText(pc.getName());
        holder.tv_point.setText(String.valueOf(pc.getPoint()));
        Glide.with(mContext).load(pc.getThumbnail()).into(holder.icon_image);
        holder.card_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ProductMaxiActivity.class);
//                intent.putExtra("ID", ppob.getId());
//                view.getContext().startActivity(intent);
                Snackbar.make(view, "ID : " + pc.getId(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pcList.size();
    }
}
