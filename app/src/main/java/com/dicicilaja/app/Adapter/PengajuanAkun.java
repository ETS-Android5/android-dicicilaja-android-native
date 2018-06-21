package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.API.Item.PengajuanAxi.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProgramMaxi.Data;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by fawazrifqi on 20/05/18.
 */

public class PengajuanAkun extends RecyclerView.Adapter<PengajuanAkun.RequestViewHolder> {
    private List<Data> program;
    private int rowLayout;
    private Context context;

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_program;
        ImageView image_program;
        TextView title_program;
        TextView value_harga;

        public RequestViewHolder(View v) {
            super(v);
            card_program = v.findViewById(R.id.card_pengajuan);
            image_program = v.findViewById(R.id.image_program);
            title_program = v.findViewById(R.id.title_program);
            value_harga = v.findViewById(R.id.value_harga);
        }
    }

    public PengajuanAkun(List<Data> program, int rowLayout, Context context) {
        this.program    = program;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PengajuanAkun.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PengajuanAkun.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengajuanAkun.RequestViewHolder holder, int position) {
        holder.title_program.setText(program.get(position).getTitleProgram());
        holder.value_harga.setText(program.get(position).getPrice());
        Picasso.with(context).load(program.get(position).getImageUrl()).into(holder.image_program);

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

