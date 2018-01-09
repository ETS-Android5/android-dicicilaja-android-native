package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import id.variable.dicicilaja.API.Item.Pengajuan;
import id.variable.dicicilaja.R;

/**
 * Created by daniellindp on 30/12/2017.
 */

public class PengajuanAdapter extends RecyclerView.Adapter<PengajuanAdapter.PengajuanViewHolder> {

    private List<Pengajuan> pengajuans;
    private int rowLayout;
    private Context context;

    public static class PengajuanViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_pengajuan;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;


        public PengajuanViewHolder(View v) {
            super(v);
            card_pengajuan  = v.findViewById(R.id.card_pengajuan);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status          = v.findViewById(R.id.status);
            harga_resi      = v.findViewById(R.id.harga_resi);
            detail_resi     = v.findViewById(R.id.detail_resi);
        }
    }

    public PengajuanAdapter(List<Pengajuan> pengajuans, int rowLayout, Context context) {
        this.pengajuans = pengajuans;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PengajuanAdapter.PengajuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PengajuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengajuanAdapter.PengajuanViewHolder holder, int position) {
        holder.resi.setText("#" + pengajuans.get(position).getTrackingId().toString());
        holder.tanggal.setText(pengajuans.get(position).getCreatedAt());
        holder.status.setText(pengajuans.get(position).getStatus());
        holder.harga_resi.setText(pengajuans.get(position).getAmount().toString());
        holder.detail_resi.setText(pengajuans.get(position).getColleteral());
    }

    @Override
    public int getItemCount() {
        return pengajuans.size();
    }


}
