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


        public PengajuanViewHolder(View v) {
            super(v);
            card_pengajuan =  v.findViewById(R.id.card_pengajuan);
            resi =  v.findViewById(R.id.resi);
            tanggal =  v.findViewById(R.id.tanggal);
            status =  v.findViewById(R.id.status);
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
//        holder.resi.setText(pengajuans.get(position).getTitle());
        holder.resi.setText("#0010001608");
        holder.tanggal.setText("06 Desember 2017");
        holder.status.setText("REALISASI");
    }

    @Override
    public int getItemCount() {
        return pengajuans.size();
    }


}
