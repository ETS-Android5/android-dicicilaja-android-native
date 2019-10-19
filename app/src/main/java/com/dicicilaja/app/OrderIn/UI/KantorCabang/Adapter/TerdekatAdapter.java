package com.dicicilaja.app.OrderIn.UI.KantorCabang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Data.CabangTerdekat.Datum;
import com.dicicilaja.app.OrderIn.Data.CabangTerdekat.Included;
import com.dicicilaja.app.R;

import java.util.List;

public class TerdekatAdapter extends RecyclerView.Adapter<TerdekatAdapter.RekomendasiViewHolder> {

    private List<Datum> dataItem;
    private List<Included> dataIncluded;
    private Context context;

    private int lastSelectedPosition = -1;

    public class RekomendasiViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout card_kantor_cabang;
        TextView nama_kantor_cabang;
        TextView alamat_kantor_cabang;
        TextView area_kantor_cabang;
        public RadioButton radioButton;

        public RekomendasiViewHolder(View v) {
            super(v);
            card_kantor_cabang = v.findViewById(R.id.card_kantor_cabang);
            nama_kantor_cabang = v.findViewById(R.id.nama_kantor_cabang);
            alamat_kantor_cabang = v.findViewById(R.id.alamat_kantor_cabang);
            area_kantor_cabang = v.findViewById(R.id.area_kantor_cabang);
            radioButton = v.findViewById(R.id.radioButton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    RekomendasiAdapter.RekomendasiViewHolder.class.notifyAll();
                }
            });
        }
    }

    public TerdekatAdapter(List<Datum> dataItems, List<Included> dataIncludeds, Context context) {
        this.dataItem = dataItems;
        this.dataIncluded = dataIncludeds;
        this.context = context;
    }

    @NonNull
    @Override
    public TerdekatAdapter.RekomendasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kantor_cabang, parent, false);
        return new TerdekatAdapter.RekomendasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TerdekatAdapter.RekomendasiViewHolder holder, int position) {
        holder.nama_kantor_cabang.setText(dataItem.get(position).getAttributes().getNama());
        holder.alamat_kantor_cabang.setText(dataItem.get(position).getAttributes().getAlamat());
        holder.area_kantor_cabang.setText(dataIncluded.get(0).getAttributes().getNama() + " • " + dataIncluded.get(1).getAttributes().getNama());
        holder.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }
}
