package com.dicicilaja.app.OrderIn.UI.KantorCabang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.Datum;
import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.Included;
import com.dicicilaja.app.R;

import java.util.List;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder> {

    private List<Datum> dataItem;
    private List<Included> dataIncluded;
    private Context context;

    public static class RekomendasiViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout card_kantor_cabang;
        TextView nama_kantor_cabang;
        TextView alamat_kantor_cabang;
        TextView area_kantor_cabang;

        public RekomendasiViewHolder(View v) {
            super(v);
            card_kantor_cabang = v.findViewById(R.id.card_kantor_cabang);
            nama_kantor_cabang = v.findViewById(R.id.nama_kantor_cabang);
            alamat_kantor_cabang = v.findViewById(R.id.alamat_kantor_cabang);
            area_kantor_cabang = v.findViewById(R.id.area_kantor_cabang);
        }
    }

    public RekomendasiAdapter(List<Datum> dataItems, List<Included> dataIncludeds, Context context) {
        this.dataItem = dataItems;
        this.dataIncluded = dataIncludeds;
        this.context = context;
    }

    @NonNull
    @Override
    public RekomendasiAdapter.RekomendasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kantor_cabang, parent, false);
        return new RekomendasiAdapter.RekomendasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekomendasiAdapter.RekomendasiViewHolder holder, int position) {
        holder.nama_kantor_cabang.setText(dataItem.get(position).getAttributes().getNama());
        holder.alamat_kantor_cabang.setText(dataItem.get(position).getAttributes().getAlamat());
        holder.area_kantor_cabang.setText(dataIncluded.get(0).getAttributes().getNama() + " • " + dataIncluded.get(1).getAttributes().getNama());
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }
}
