package com.dicicilaja.app.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.InfoJaringan;
import com.dicicilaja.app.R;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class RekanBisnisAdapter extends RecyclerView.Adapter<RekanBisnisAdapter.RequestViewHolder> {
    private List<InfoJaringan> rekanBisnis;
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

    public RekanBisnisAdapter(List<InfoJaringan> rekanBisnis, int rowLayout, Context context) {
        this.rekanBisnis = rekanBisnis;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RekanBisnisAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RekanBisnisAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RekanBisnisAdapter.RequestViewHolder holder, int position) {
        holder.nama.setText(rekanBisnis.get(position).getNamaLengkap());
        holder.id_axi.setText("("+rekanBisnis.get(position).getIdAxi()+")");
        holder.status.setText(rekanBisnis.get(position).getPointReward());
        holder.status_poin.setText(rekanBisnis.get(position).getPointTrip());
    }

    @Override
    public int getItemCount() {
        return rekanBisnis.size();
    }
}

