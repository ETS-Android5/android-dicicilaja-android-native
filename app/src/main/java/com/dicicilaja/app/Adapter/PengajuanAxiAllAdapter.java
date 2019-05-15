package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.API.Item.PengajuanAxi.Datum;
import com.dicicilaja.app.Activity.DetailRequestActivity;
import com.dicicilaja.app.R;

/**
 * Created by fawazrifqi on 03/05/18.
 */

public class PengajuanAxiAllAdapter extends RecyclerView.Adapter<PengajuanAxiAllAdapter.RequestViewHolder>  {
    private List<Datum> pengajuan;
    private int rowLayout;
    private Context context;

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_pengajuan;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;

        public RequestViewHolder(View v) {
            super(v);
            card_pengajuan  = v.findViewById(R.id.card_pengajuan);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status          = v.findViewById(R.id.status);
            harga_resi      = v.findViewById(R.id.harga_resi);
            detail_resi     = v.findViewById(R.id.detail_resi);
            nama_resi     = v.findViewById(R.id.nama_resi);

            card_pengajuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailRequestActivity.class);
                    intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(getAdapterPosition()).getId().toString());
                    context.startActivity(intent);
                }
            });
        }
    }

    public PengajuanAxiAllAdapter(List<Datum> requests, int rowLayout, Context context) {
        this.pengajuan = requests;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PengajuanAxiAllAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PengajuanAxiAllAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengajuanAxiAllAdapter.RequestViewHolder holder, int position) {
        holder.resi.setText("#" + pengajuan.get(position).getTrackingId().toString());
        holder.tanggal.setText(pengajuan.get(position).getCreatedAt());
        holder.status.setText(pengajuan.get(position).getStatus());
        holder.harga_resi.setText(pengajuan.get(position).getAmount());
        holder.detail_resi.setText(pengajuan.get(position).getClientName());
        holder.nama_resi.setText(pengajuan.get(position).getProgram());
        switch(pengajuan.get(position).getStatus()) {
            case "Terkirim":
                holder.status.setBackgroundResource(R.drawable.capsule_terkirim);
                break;
            case "Verifikasi":
                holder.status.setBackgroundResource(R.drawable.capsule_verifikasi);
                break;
            case "Proses":
                holder.status.setBackgroundResource(R.drawable.capsule_proses);
                break;
            case "Survey":
                holder.status.setBackgroundResource(R.drawable.capsule_survey);
                break;
            case "Pending":
                holder.status.setBackgroundResource(R.drawable.capsule_pending);
                break;
            case "Analisa Kredit":
                holder.status.setBackgroundResource(R.drawable.capsule_analisa);
                break;
            case "Ditolak":
                holder.status.setBackgroundResource(R.drawable.capsule_ditolak);
                break;
            case "Pencairan":
                holder.status.setBackgroundResource(R.drawable.capsule_pencairan);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return pengajuan.size();
    }

    public void refreshAdapter(List<Datum> data) {
        this.pengajuan.addAll(data);
        notifyItemRangeChanged(0, this.pengajuan.size());
    }

}
