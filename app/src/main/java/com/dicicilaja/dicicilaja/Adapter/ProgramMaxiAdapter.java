package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.Data;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class ProgramMaxiAdapter extends RecyclerView.Adapter<ProgramMaxiAdapter.RequestViewHolder> {
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

    public ProgramMaxiAdapter(List<Data> program, int rowLayout, Context context) {
        this.program    = program;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ProgramMaxiAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProgramMaxiAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgramMaxiAdapter.RequestViewHolder holder, int position) {
        holder.title_program.setText(program.get(position).getTitleProgram());
        holder.value_harga.setText(program.get(position).getPrice());
        Picasso.with(context).load(program.get(position).getImageUrl()).into(holder.image_program);

    }

    @Override
    public int getItemCount() {
        if(program.size() > 2){
            return 3;
        }else{
            return program.size();
        }
    }
}
