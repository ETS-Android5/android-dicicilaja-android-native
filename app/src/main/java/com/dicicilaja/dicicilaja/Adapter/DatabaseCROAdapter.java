package com.dicicilaja.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Item.DatabaseCRO.Datum;
import com.dicicilaja.dicicilaja.R;

/**
 * Created by ziterz on 1/25/2018.
 */

public class DatabaseCROAdapter extends RecyclerView.Adapter<DatabaseCROAdapter.DatabaseCROViewHolder> {

    private List<Datum> databaseEmployees;
    private int rowLayout;
    private Context context;

    public static class DatabaseCROViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_database_crh;
        TextView nama;
        TextView nik;
        TextView area;
        TextView jabatan;

        public DatabaseCROViewHolder(View v) {
            super(v);
            card_database_crh  = v.findViewById(R.id.card_database_crh);
            nama               = v.findViewById(R.id.nama);
            nik                = v.findViewById(R.id.nik);
            area               = v.findViewById(R.id.area);
            jabatan            = v.findViewById(R.id.jabatan);
        }
    }

    public DatabaseCROAdapter(List<Datum> databaseEmployees, int rowLayout, Context context) {
        this.databaseEmployees = databaseEmployees;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public DatabaseCROAdapter.DatabaseCROViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DatabaseCROAdapter.DatabaseCROViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DatabaseCROAdapter.DatabaseCROViewHolder holder, int position) {
        holder.nama.setText(databaseEmployees.get(position).getName().toString());
        holder.nik.setText(databaseEmployees.get(position).getUserId().toString());
        holder.area.setText("Area");
        holder.jabatan.setText("Jabatan");
    }

    @Override
    public int getItemCount() {
        return databaseEmployees.size();
    }
}
