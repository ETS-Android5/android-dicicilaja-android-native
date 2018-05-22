package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemInfoJaringan.Data;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class InfoJaringanAxiAdapter extends RecyclerView.Adapter<InfoJaringanAxiAdapter.RequestViewHolder> {
    private List<Data> infoJaringan;
    private int rowLayout;
    private Context context;


    public class RequestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_rb;
        TextView nama;
        TextView id_axi;
        TextView status;
        TextView status_poin;

        public RequestViewHolder(View v) {
            super(v);
            card_rb  = v.findViewById(R.id.card_rb);
            nama            = v.findViewById(R.id.nama);
            id_axi         = v.findViewById(R.id.id_axi);
            status          = v.findViewById(R.id.status);
            status_poin      = v.findViewById(R.id.status_poin);
        }
    }

    public InfoJaringanAxiAdapter(List<Data> infoJaringan, int rowLayout, Context context) {
        this.infoJaringan = infoJaringan;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public InfoJaringanAxiAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new InfoJaringanAxiAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoJaringanAxiAdapter.RequestViewHolder holder, int position) {
        holder.nama.setText(infoJaringan.get(position).getNamaLengkap());
        holder.id_axi.setText("("+infoJaringan.get(position).getIdAxi()+")");
        holder.status.setText(infoJaringan.get(position).getPointReward());
        holder.status_poin.setText(infoJaringan.get(position).getPointTrip());
    }

    @Override
    public int getItemCount() {
        return infoJaringan.size();
    }
}
