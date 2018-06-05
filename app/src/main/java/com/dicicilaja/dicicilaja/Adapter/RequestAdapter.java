package com.dicicilaja.dicicilaja.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.dicicilaja.API.Item.Request.Datum;
import com.dicicilaja.dicicilaja.Activity.DetailRequestActivity;
import com.dicicilaja.dicicilaja.Model.ResRequestProcess;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.ApiUtils;
import com.dicicilaja.dicicilaja.Remote.ClaimProcess;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ziterz on 30/12/2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> implements Filterable {

    private Context context;
    private List<Datum> requests;
    private List<Datum> dataListFiltered;
    private RequestAdapterListener listener;
    ClaimProcess claimProcess;
    String apiKey;

    SessionManager session;

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;

        public RequestViewHolder(View v) {
            super(v);
            card_view  = v.findViewById(R.id.card_view);
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
                    claimProcess = ApiUtils.getClaim();

                    session = new SessionManager(context);
                    apiKey = "Bearer " + session.getToken();

                    if (dataListFiltered.get(getAdapterPosition()).getStatus().toString().equals("Terkirim")) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                        // Setting Dialog Title
                        alertDialog.setTitle("Proses Pengajuan");

                        // Setting Dialog Message
                        alertDialog.setMessage("Dengan menekan tombol \"lanjutkan\" Anda bertanggung jawab untuk segera menindaklanjuti pengajuan ini.");



                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("LANJUTKAN", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String transaction_id = dataListFiltered.get(getAdapterPosition()).getId().toString();
                                String assigned_id = session.getUserId();
                                String notes = "-";
                                String claim = "1";
//                                            Toast.makeText(getContext(),"api key : " + apiKey + " transcation_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + notes,Toast.LENGTH_LONG).show();
                                doProcess(apiKey, transaction_id, assigned_id, notes, claim);
                                Log.i("firebase_login", "transaction_id : "  + transaction_id + "assigned_id" + assigned_id + "notes" + notes + "claim" + claim);
                                Intent intent = new Intent(context, DetailRequestActivity.class);

                                intent.putExtra("EXTRA_REQUEST_ID", dataListFiltered.get(getAdapterPosition()).getId().toString());
                                ((Activity) context).startActivity(intent);
                            }
                        });

                        // Setting Negative "NO" Button
//                                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                });

                        // Showing Alert Message
                        alertDialog.show();
                    }else if (dataListFiltered.get(getAdapterPosition()).getStatus().toString().equals("Verifikasi")) {
                        Intent intent = new Intent(context, DetailRequestActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", dataListFiltered.get(getAdapterPosition()).getId().toString());
                        ((Activity) context).startActivity(intent);
                    }

                }
            });
        }
    }

    public RequestAdapter(Context context, List<Datum> requests, RequestAdapterListener listener) {
        this.requests = requests;
        this.listener = listener;
        this.context = context;
        this.dataListFiltered = requests;
    }

    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pengajuan, parent, false);
        return new RequestAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestAdapter.RequestViewHolder holder, int position) {
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
                    dataListFiltered = requests;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : requests) {

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

    public interface RequestAdapterListener {
        void onDataSelected(Datum datum);
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String claim) {
        Call<ResRequestProcess> call = claimProcess.assign(apiKey,transaction_id, assigned_id, notes, claim);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {

            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

            }
        });
    }

}
