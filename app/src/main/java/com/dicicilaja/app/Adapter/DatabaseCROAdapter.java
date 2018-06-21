package com.dicicilaja.app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.app.API.Item.DatabaseCRO.Datum;
import com.dicicilaja.app.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ziterz on 1/25/2018.
 */

public class DatabaseCROAdapter extends RecyclerView.Adapter<DatabaseCROAdapter.DatabaseCROViewHolder> implements Filterable {

    private Context context;
    private List<Datum> dataList;
    private List<Datum> dataListFiltered;
    private CROAdapterListener listener;

    public class DatabaseCROViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_database_crh;
        TextView nama;
        TextView nik;
        TextView branch;
        Button button_pilih;

        public DatabaseCROViewHolder(View v) {
            super(v);
            card_database_crh  = v.findViewById(R.id.card_database_crh);
            nama               = v.findViewById(R.id.nama);
            nik                = v.findViewById(R.id.nik);
            branch               = v.findViewById(R.id.branch);
            button_pilih        = v.findViewById(R.id.button_pilih);

            button_pilih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onDataSelected(dataListFiltered.get(getAdapterPosition()));
                    Intent intent = new Intent();
                    intent.putExtra("ID_DATABASE", dataListFiltered.get(getAdapterPosition()).getUserId());
                    ((Activity) context).setResult(RESULT_OK, intent);
                    ((Activity) context).finish();

                }
            });
        }
    }

    public DatabaseCROAdapter(Context context, List<Datum> dataList, CROAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.dataList = dataList;
        this.dataListFiltered = dataList;
    }

    @Override
    public DatabaseCROAdapter.DatabaseCROViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_database_crh, parent, false);
        return new DatabaseCROAdapter.DatabaseCROViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DatabaseCROAdapter.DatabaseCROViewHolder holder, int position) {
        final Datum datum = dataListFiltered.get(position);
        holder.nama.setText(datum.getName().toString());
        holder.nik.setText(datum.getUserId().toString());
        holder.branch.setText(datum.getBranch().toString());
    }


    @Override
    public int getItemCount() {
        return dataListFiltered.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataListFiltered = dataList;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : dataList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBranch().toLowerCase().contains(charString.toLowerCase()) || row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getUserId().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    dataListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataListFiltered = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CROAdapterListener {
        void onDataSelected(Datum datum);
    }

}
