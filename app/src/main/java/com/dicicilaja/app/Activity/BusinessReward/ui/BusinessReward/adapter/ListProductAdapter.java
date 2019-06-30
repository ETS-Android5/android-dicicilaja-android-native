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
    int idProduk;
    List<Datum_> pcList;
    List<Included> pcList2;
    Context mContext;
    Relationships re;
    Integer id;
    Integer size;
    String nama;
    int no = 0;

    public ListProductAdapter(List<Datum_> data, List<Included> pc2, Context baseContext) {
        this.pcList = data;
        this.pcList2 = pc2;
        this.mContext = baseContext;
    }

//    public ListProductAdapter(List<Datum_> data, List<Included> pc2, Context baseContext) {
//        this.pcList = data;
//        this.pcList2 = pc2;
//        this.mContext = baseContext;
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

        Log.d("GETID", " 1nya: " + pcList.size());
        Log.d("GETID", " 2nya: " + pcList2.size());
//        for (int i = 0; i < pcList.size();i++) {
            for (int j = 0; j < pcList2.size(); j++) {
                Log.d("IDPRODUKNYA", String.valueOf(idProduk));
                Log.d("IDPRODUKNYA", String.valueOf(pcList2.get(j).getId()));
                if (pcList.get(no).getId() == pcList2.get(j).getId()) {
                    if (pcList2.get(j).getAttributes().getNama().length() >= 16) {
                        nama = pcList2.get(j).getAttributes().getNama().substring(0, 15) + "...";
                    } else {
                        nama = pcList2.get(j).getAttributes().getNama();
                    }
                    String title = nama;
                    holder.tv_title.setText(title);
                    holder.tv_point.setText(String.valueOf(pcList2.get(j).getAttributes().getPoint()));
                    Glide.with(mContext).load(pcList2.get(j).getAttributes().getFoto()).into(holder.iconImage);
                    int finalj = j;
                    holder.card_pc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, DetailProductActivity.class);
                            intent.putExtra("ID", pcList2.get(finalj).getId());
                            view.getContext().startActivity(intent);
                        }
                    });
//                    break;
                }
            }
//        }
        no++;
    }

    @Override
    public int getItemCount() {
        return pcList.size();
    }
}
