package com.dicicilaja.app.NewSimulation.ui.newsimulation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.dicicilaja.app.NewSimulation.ui.newcolleteral.NewColleteralActivity;
import com.dicicilaja.app.NewSimulation.data.TipeObjek.DataItem;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TipeObjekAdapter extends RecyclerView.Adapter<TipeObjekAdapter.ViewHolder> {

    private Context context;
    private int rowLayout;
    private List<DataItem> dataItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView title;
        ImageView icon;

        public ViewHolder(View v) {
            super(v);
            card_view = v.findViewById(R.id.card_view);
            title = v.findViewById(R.id.title);
            icon = v.findViewById(R.id.icon);
        }
    }

    public TipeObjekAdapter(List<DataItem> dataItems, int rowLayout, Context context) {
        this.rowLayout = rowLayout;
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public TipeObjekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tipe_objek, parent, false);
        return new TipeObjekAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipeObjekAdapter.ViewHolder holder, int position) {

        if (dataItems.get(position).getAttributes().getNama().equals("mcy")) {
            holder.title.setText("Sepeda Motor");
            Picasso.get().load(R.drawable.ic_motor_sports).into(holder.icon);

        } else if (dataItems.get(position).getAttributes().getNama().equals("car")) {
            holder.title.setText("Mobil");
            Picasso.get().load(R.drawable.ic_automobile).into(holder.icon);

        }

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewColleteralActivity.class);
                intent.putExtra("type", dataItems.get(position).getAttributes().getNama());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
}
