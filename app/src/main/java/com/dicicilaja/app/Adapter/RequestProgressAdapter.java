package com.dicicilaja.app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.app.API.Item.RequestProgress.Datum;
import com.dicicilaja.app.Activity.DetailRequestActivity;
import com.dicicilaja.app.R;

/**
 * Created by ziterz on 1/25/2018.
 */

public class RequestProgressAdapter extends RecyclerView.Adapter<RequestProgressAdapter.RequestProgressViewHolder> implements Filterable {

    private Context context;
    private List<Datum> requestProgresses;
    private List<Datum> dataListFiltered;
    private RequestProgressAdapterListener listener;
    String apiKey;

    public class RequestProgressViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;

        public RequestProgressViewHolder(View v) {
            super(v);
            card_view       = v.findViewById(R.id.card_view);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status          = v.findViewById(R.id.status);
            harga_resi      = v.findViewById(R.id.harga_resi);
            detail_resi     = v.findViewById(R.id.detail_resi);
            nama_resi       = v.findViewById(R.id.nama_resi);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDataSelected(dataListFiltered.get(getAdapterPosition()));
                    Intent intent = new Intent(context, DetailRequestActivity.class);
                    intent.putExtra("EXTRA_REQUEST_ID", dataListFiltered.get(getAdapterPosition()).getId().toString());
                    intent.putExtra("STATUS", true);
                    ((Activity) context).startActivity(intent);
                }
            });
        }
    }

    public RequestProgressAdapter(Context context, List<Datum> requestProgresses, RequestProgressAdapterListener listener) {
        this.requestProgresses = requestProgresses;
        this.listener = listener;
        this.context = context;
        this.dataListFiltered = requestProgresses;
    }

    @Override
    public RequestProgressAdapter.RequestProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pengajuan_progress, parent, false);
        return new RequestProgressAdapter.RequestProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestProgressAdapter.RequestProgressViewHolder holder, int position) {
        final Datum datum = dataListFiltered.get(position);
        holder.resi.setText("#" + datum.getTrackingId().toString());
        holder.tanggal.setText(datum.getCreatedAt());
        holder.status.setText(datum.getStatus());
        holder.harga_resi.setText(datum.getBranch());
        holder.detail_resi.setText(datum.getClient_name());
        holder.nama_resi.setText(datum.getProgram());
        switch(datum.getStatus()) {
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
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataListFiltered = requestProgresses;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : requestProgresses) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getStatus().toLowerCase().contains(charString.toLowerCase()) || row.getBranch().toLowerCase().contains(charString.toLowerCase()) || row.getClient_name().toLowerCase().contains(charString.toLowerCase()) || row.getTrackingId().toString().toLowerCase().contains(charString.toLowerCase()) || row.getProgram().toLowerCase().contains(charString.toLowerCase()) || row.getCreatedAt().toLowerCase().contains(charString.toLowerCase())) {
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

    @Override
    public int getItemCount() {
        return dataListFiltered.size();
    }

    public interface RequestProgressAdapterListener {
        void onDataSelected(Datum datum);
    }
}
