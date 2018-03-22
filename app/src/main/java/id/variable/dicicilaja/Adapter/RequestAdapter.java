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
import id.variable.dicicilaja.API.Item.Request.Datum;
import id.variable.dicicilaja.API.Item.Request.Request;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 30/12/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Datum> requests;
    private int rowLayout;
    private Context context;

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

    public RequestAdapter(List<Datum> requests, int rowLayout, Context context) {
        this.requests = requests;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RequestAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestAdapter.RequestViewHolder holder, int position) {
        holder.resi.setText("#" + requests.get(position).getTrackingId().toString());
        holder.tanggal.setText(requests.get(position).getCreatedAt());
        holder.status.setText(requests.get(position).getStatus());
        holder.harga_resi.setText(requests.get(position).getBranch());
        holder.detail_resi.setText(requests.get(position).getColleteral());
        holder.nama_resi.setText(requests.get(position).getProgram());
        switch(requests.get(position).getStatus()) {
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
        return requests.size();
    }

}
