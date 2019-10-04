package com.dicicilaja.app.OrderIn.UI.KantorCabang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.CabangRekomendasi;
import com.dicicilaja.app.R;

import java.util.List;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.NotifViewHolder> {

    private List<CabangRekomendasi> cabangRekomendasiList;
    private int rowLayout;
    private Context context;

    public static class NotifViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_kantor_cabang;
        TextView nama_kantor_cabang;
        TextView alamat_kantor_cabang;
        TextView area_kantor_cabang;

        public NotifViewHolder(View v) {
            super(v);
            card_kantor_cabang  = v.findViewById(R.id.card_kantor_cabang);
            nama_kantor_cabang            = v.findViewById(R.id.nama_kantor_cabang);
            alamat_kantor_cabang         = v.findViewById(R.id.alamat_kantor_cabang);
            area_kantor_cabang          = v.findViewById(R.id.area_kantor_cabang);
        }
    }

    public RekomendasiAdapter(List<CabangRekomendasi> cabangRekomendasiList, int rowLayout, Context context) {
        this.cabangRekomendasiList = cabangRekomendasiList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RekomendasiAdapter.NotifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RekomendasiAdapter(view);
    }

    @Override
    public void onBindViewHolder(RekomendasiAdapter.NotifViewHolder holder, int position) {
        holder.nama_kantor_cabang.setText(cabangRekomendasiList.get(0).getData().get(position).getAttributes().getNama());
        holder.alamat_kantor_cabang.setText(cabangRekomendasiList.get(0).getData().get(position).getAttributes().getAlamat());
        holder.area_kantor_cabang.setText(cabangRekomendasiList.get(0).getIncluded().get(0).getAttributes().getNama() + cabangRekomendasiList.get(0).getIncluded().get(position).getAttributes().getNama());
        holder.detail_resi.setText(notifs.get(position).getDetail());
        switch(notifs.get(position).getStatus()) {
            case "Terkirim":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_terkirim);
                break;
            case "Verifikasi":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_verifikasi);
                break;
            case "Proses":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_proses);
                break;
            case "Survey":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_survey);
                break;
            case "Pending":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_pending);
                break;
            case "Analisa Kredit":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_analisa);
                break;
            case "Ditolak":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_ditolak);
                break;
            case "Pencairan":
                holder.status_notif.setBackgroundResource(R.drawable.capsule_pencairan);
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }
}
