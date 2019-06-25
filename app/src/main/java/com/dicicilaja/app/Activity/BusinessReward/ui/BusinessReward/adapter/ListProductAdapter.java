package com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum_;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Relationships;
import com.dicicilaja.app.Activity.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.R;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.MyViewHolder> {

//    List<com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Attributes> pcList;
    List<Datum> pcList;
    List<Included> pcList2;
    Context mContext;
    Relationships re;
    Integer id;
    Integer size;

    public ListProductAdapter(List<Included> pc2, List<Datum> pc, Context baseContext) {
        this.pcList2 = pc2;
        this.pcList = pc;
        this.mContext = baseContext;
    }

//    public ListProductAdapter(List<Included> pc2, Relationships relationships, Context baseContext) {
//        this.pcList2 = pc2;
//        this.mContext = baseContext;
//        this.re = relationships;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_point;
        public CardView card_pc;
        public ImageView iconImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.card_pc = itemView.findViewById(R.id.card_pc);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_point = itemView.findViewById(R.id.tv_point);
            this.iconImage = itemView.findViewById(R.id.icon_image);
        }
    }

    @NonNull
    @Override
    public ListProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_catalog, null);
        TextView tv_title = v.findViewById(R.id.tv_title);
        TextView tv_point = v.findViewById(R.id.tv_point);
        return new ListProductAdapter.MyViewHolder(v);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull ListProductAdapter.MyViewHolder holder, int position) {
//        final Datum_ pc = pcList.get(position);

//        final Included pc2 = pcList2.get(position);

//        for (Included idnya : pcList2){
//            if(id == idnya.getId()){
//                holder.tv_title.setText(String.valueOf(idnya.getAttributes().getNama()));
//                holder.tv_point.setText(String.valueOf(idnya.getAttributes().getPoint()));
//                holder.card_pc.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(mContext, DetailProductActivity.class);
//                        view.getContext().startActivity(intent);
//                    }
//                });
//            }
//        }

//        pcList2.get();
        for (int i = 0; i < pcList2.size(); i++){
//        for (int i = 0; i < re.getProductCatalogs().getData().size(); i++){

//                Log.d("GETID", " posisi: " + position);
//                Log.d("GETID", " sizenya: " + j);
                if(pcList.get(position).getId() == pcList2.get(i).getId()){
                    holder.tv_title.setText(String.valueOf(pcList2.get(i).getAttributes().getNama()));
                    holder.tv_point.setText(String.valueOf(pcList2.get(i).getAttributes().getPoint()));
                    Glide.with(mContext).load(pcList2.get(i).getAttributes().getFoto()).into(holder.iconImage);
                    int finalI = i;
                    holder.card_pc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, DetailProductActivity.class);
                            intent.putExtra("ID", pcList2.get(finalI).getId());
                            view.getContext().startActivity(intent);
                        }
                    });
                }
//            }
//            Log.d("GETID", " size: " + size);
//            Log.d("GETID", " getid: " + pcList2.get(i).getId() + " id: " + re.getProductCatalogs().getData().get(position).getId());
        }

//            Toast.makeText(mContext, pc.getId(), 100);
    }

    @Override
    public int getItemCount() {
        return pcList2.size();
    }
}
