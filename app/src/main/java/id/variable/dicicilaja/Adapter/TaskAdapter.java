package id.variable.dicicilaja.Adapter;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.API.Item.Task.Datum;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.ClaimProcess;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ziterz on 1/25/2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> implements Filterable {
    private Context context;
    private List<Datum> tasks;
    private List<Datum> dataListFiltered;
    private TaskAdapterListener listener;
    ClaimProcess claimProcess;
    String apiKey;

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView resi;
        TextView tanggal;
        TextView status;
        TextView harga_resi;
        TextView detail_resi;
        TextView nama_resi;



        public TaskViewHolder(View v) {
            super(v);
            card_view  = v.findViewById(R.id.card_view);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status          = v.findViewById(R.id.status);
            harga_resi      = v.findViewById(R.id.harga_resi);
            detail_resi     = v.findViewById(R.id.detail_resi);
            nama_resi     = v.findViewById(R.id.nama_resi);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDataSelected(dataListFiltered.get(getAdapterPosition()));
                    claimProcess = ApiUtils.getClaim();

                    final SessionManager session = new SessionManager(context);
                    apiKey = "Bearer " + session.getToken();

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                    // Setting Dialog Title
                    alertDialog.setTitle("Proses Pengajuan");

                    // Setting Dialog Message
                    alertDialog.setMessage("Dengan menekan tombol \"lanjutkan\" Anda bertanggung jawab untuk segera menindaklanjuti pengajuan ini.");


                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("LANJUTKAN", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                            String transaction_id = dataListFiltered.get(getAdapterPosition()).getTransactionId().toString();
//                            String assigned_id = session.getUserId();
//                            String notes = "-";

//                            doProcess(apiKey, transaction_id, assigned_id, notes, claim);
                            Intent intent = new Intent(context, DetailRequestActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", dataListFiltered.get(getAdapterPosition()).getTransactionId().toString());
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
                }
            });
        }
    }

    public TaskAdapter(Context context, List<Datum> tasks, TaskAdapterListener listener) {
        this.tasks = tasks;
        this.listener = listener;
        this.context = context;
        this.dataListFiltered = tasks;
    }

    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pengajuan, parent, false);
        return new TaskAdapter.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.TaskViewHolder holder, int position) {
        final Datum datum = dataListFiltered.get(position);
        holder.resi.setText("#" + datum.getTrackingId().toString());
        holder.tanggal.setText(datum.getCreatedAt());
        holder.status.setText(datum.getStatus());
        holder.harga_resi.setText(datum.getBranch().toString());
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
                    dataListFiltered = tasks;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : tasks) {

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

    public interface TaskAdapterListener {
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
