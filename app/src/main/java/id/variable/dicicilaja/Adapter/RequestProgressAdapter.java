package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import id.variable.dicicilaja.API.Item.PengajuanProgress;
import id.variable.dicicilaja.API.Item.RequestProgress.Datum;
import id.variable.dicicilaja.API.Item.RequestProgress.RequestProgress;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 1/25/2018.
 */

public class RequestProgressAdapter extends RecyclerView.Adapter<RequestProgressAdapter.RequestProgressViewHolder> {
    private List<Datum> requestProgresses;
    private int rowLayout;
    private Context context;

    public static class RequestProgressViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_pengajuan;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;



        public RequestProgressViewHolder(View v) {
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

    public RequestProgressAdapter(List<Datum> requestProgresses, int rowLayout, Context context) {
        this.requestProgresses = requestProgresses;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RequestProgressAdapter.RequestProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RequestProgressAdapter.RequestProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestProgressAdapter.RequestProgressViewHolder holder, int position) {
        holder.resi.setText("#" + requestProgresses.get(position).getTrackingId().toString());
        holder.tanggal.setText(requestProgresses.get(position).getCreatedAt());
        holder.status.setText(requestProgresses.get(position).getStatus());
        holder.harga_resi.setText(requestProgresses.get(position).getAmount().toString());
        holder.detail_resi.setText(requestProgresses.get(position).getClient_name());
        holder.nama_resi.setText(requestProgresses.get(position).getProgram());
        switch(requestProgresses.get(position).getStatus()) {
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
        return requestProgresses.size();
    }
}
