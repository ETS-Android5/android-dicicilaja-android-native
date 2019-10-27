package com.dicicilaja.app.OrderIn.UI.KantorCabang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.Datum;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.OrderIn.UI.KonfirmasiPengajuanActivity;
import com.dicicilaja.app.R;

import java.util.List;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder> {

    private List<Datum> dataItem;
    private Context context;

    SessionOrderIn session;

    public class RekomendasiViewHolder extends RecyclerView.ViewHolder {

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

    public RekomendasiAdapter(List<Datum> dataItems, Context context) {
        this.dataItem = dataItems;
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
        session = new SessionOrderIn(context);

        holder.nama_kantor_cabang.setText(dataItem.get(position).getAttributes().getNama());
        holder.alamat_kantor_cabang.setText(dataItem.get(position).getAttributes().getAlamat());
        holder.area_kantor_cabang.setText(toTitleCase(dataItem.get(position).getAttributes().getKota() + " • " + dataItem.get(position).getAttributes().getDistrik()));
        holder.card_kantor_cabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setBranch_id(dataItem.get(position).getAttributes().getKode());
                session.setBranch(dataItem.get(position).getAttributes().getNama());
                session.setBranch_address(dataItem.get(position).getAttributes().getAlamat());
                session.setBranch_district(toTitleCase(dataItem.get(position).getAttributes().getKota() + " • " + dataItem.get(position).getAttributes().getDistrik()));

                Intent intent = new Intent(context, KonfirmasiPengajuanActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }
}
