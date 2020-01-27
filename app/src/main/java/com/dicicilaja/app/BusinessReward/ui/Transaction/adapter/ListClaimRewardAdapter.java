package com.dicicilaja.app.BusinessReward.ui.Transaction.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.API.Model.Transaction;
import com.dicicilaja.app.Adapter.RequestAdapter;
import com.dicicilaja.app.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Included;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.DetailTransactionActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.Model.ResRequestProcess;
import com.dicicilaja.app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListClaimRewardAdapter extends RecyclerView.Adapter<ListClaimRewardAdapter.MyViewHolder> implements Filterable {
    private static final String TAG = ListClaimRewardAdapter.class.getSimpleName();

    Context mContext;
    List<Datum> requests;
    List<Datum> clList;
    List<Included> includedList;
    private TransactionActivity listener;
    String statusnya;

    public ListClaimRewardAdapter(Context baseContext, List<Datum> dataItems, List<Included> includedList, TransactionActivity listener) {
        this.clList = dataItems;
        this.includedList = includedList;
        this.mContext = baseContext;
        this.listener = listener;
        this.requests = dataItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_tgl;
        public TextView tv_point;
        public TextView tv_merk;
        public TextView tv_status;
        public LinearLayout card;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_tgl = itemView.findViewById(R.id.tgl);
            this.tv_point = itemView.findViewById(R.id.point);
            this.tv_merk = itemView.findViewById(R.id.merk);
            this.tv_status = itemView.findViewById(R.id.status);
            this.card = itemView.findViewById(R.id.card_trans);
        }
    }

    @NonNull
    @Override
    public ListClaimRewardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaksi, null);
        return new ListClaimRewardAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClaimRewardAdapter.MyViewHolder holder, int position) {
        final Datum cl = clList.get(position);
        int inclPosition = -1;

        if (inclPosition == -1) {
        }

        holder.tv_tgl.setText(cl.getAttributes().getUpdatedAtstring());

        String curString = cl.getAttributes().getCreatedAt();
        String[] separated = curString.split("T");
        String tgl = separated[0];
        String[] separated2 = tgl.split("-");
        String bulan = separated2[1];
        String tanggal = separated2[2];

        String finalBulan = null;

        if (bulan.equals("01")) {
            finalBulan = "Jan";
        } else if (bulan.equals("02")) {
            finalBulan = "Feb";
        } else if (bulan.equals("03")) {
            finalBulan = "Mar";
        } else if (bulan.equals("04")) {
            finalBulan = "Apr";
        } else if (bulan.equals("05")) {
            finalBulan = "Mei";
        } else if (bulan.equals("06")) {
            finalBulan = "Jun";
        } else if (bulan.equals("07")) {
            finalBulan = "Juli";
        } else if (bulan.equals("08")) {
            finalBulan = "Agus";
        } else if (bulan.equals("09")) {
            finalBulan = "Sep";
        } else if (bulan.equals("10")) {
            finalBulan = "Okt";
        } else if (bulan.equals("11")) {
            finalBulan = "Nov";
        } else if (bulan.equals("12")) {
            finalBulan = "Des";
        }

        holder.tv_tgl.setText(tanggal + " " + finalBulan);

        holder.tv_point.setText(cl.getAttributes().getPoint() + " POINT");

        String value_title = cl.getAttributes().getNama();
        if (value_title.length() >= 35) {
            value_title = value_title.substring(0, 36) + "...";
            holder.tv_merk.setText(value_title);
        } else {
            holder.tv_merk.setText(value_title);
        }

        int status_id = cl.getAttributes().getStatusId();
        switch (status_id) {
            case 5:
                holder.tv_status.setText("Sedang diproses");
                break;
            case 6:
                holder.tv_status.setText("Sedang diproses");
                break;
            case 7:
                holder.tv_status.setText("Packing");
                break;
            case 8:
                holder.tv_status.setText("Dikirim");
                break;
            case 9:
                holder.tv_status.setText("Sudah sampai di cabang");
                break;
            case 10:
                holder.tv_status.setText("Batal");
                break;
            default:
                break;
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailTransactionActivity.class);
                intent.putExtra("ID", String.valueOf(cl.getId()));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (clList == null) {
            return 0;
        } else {
            return clList.size();
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    clList = requests;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : requests) {

                        filteredList.add(row);
                    }

                    clList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = clList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clList = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ListClaimRewardAdapterListener {
        void onDataSelected(com.dicicilaja.app.API.Model.Request.Datum datum);
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String claim) {
    }

    public void refreshAdapter(List<Datum> data, List<Included> dataIncl) {
        includedList.addAll(dataIncl);
        for (Datum datum : data) {
            if (datum.getType().equals("claim_rewards"))
                clList.add(datum);
        }
        notifyDataSetChanged();
    }
}
