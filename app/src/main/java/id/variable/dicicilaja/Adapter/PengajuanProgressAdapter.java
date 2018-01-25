package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.variable.dicicilaja.API.Item.Pengajuan;
import id.variable.dicicilaja.API.Item.PengajuanProgress;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 1/25/2018.
 */

public class PengajuanProgressAdapter extends RecyclerView.Adapter<PengajuanProgressAdapter.PengajuanProgressViewHolder> {
    private List<PengajuanProgress> pengajuanProgresses;
    private int rowLayout;
    private Context context;

    public static class PengajuanProgressViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_pengajuan;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;



        public PengajuanProgressViewHolder(View v) {
            super(v);
            card_pengajuan  = v.findViewById(R.id.card_pengajuan);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status          = v.findViewById(R.id.status);
            harga_resi      = v.findViewById(R.id.harga_resi);
            detail_resi     = v.findViewById(R.id.detail_resi);
            nama_resi     = v.findViewById(R.id.nama_resi);
        }
    }

    public PengajuanProgressAdapter(List<PengajuanProgress> pengajuanProgresses, int rowLayout, Context context) {
        this.pengajuanProgresses = pengajuanProgresses;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PengajuanProgressAdapter.PengajuanProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PengajuanProgressAdapter.PengajuanProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengajuanProgressAdapter.PengajuanProgressViewHolder holder, int position) {
        holder.resi.setText("#" + pengajuanProgresses.get(position).getTrackingId().toString());
        holder.tanggal.setText(pengajuanProgresses.get(position).getCreatedAt());
        holder.status.setText(pengajuanProgresses.get(position).getStatus());
        holder.harga_resi.setText(pengajuanProgresses.get(position).getAmount().toString());
        holder.detail_resi.setText(pengajuanProgresses.get(position).getColleteral());
        holder.nama_resi.setText(pengajuanProgresses.get(position).getProgram());
        switch(pengajuanProgresses.get(position).getStatus()) {
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
        return pengajuanProgresses.size();
    }
}
