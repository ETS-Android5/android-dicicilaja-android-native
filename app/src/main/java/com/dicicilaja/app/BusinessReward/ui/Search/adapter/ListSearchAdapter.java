package com.dicicilaja.app.BusinessReward.ui.Search.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity.BusinesRewardActivity;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.DetailTransactionActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.adapter.ListClaimRewardAdapter;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSearchAdapter extends RecyclerView.Adapter<ListSearchAdapter.MyViewHolder> implements Filterable {
    Context mContext;
    List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> requests;
    List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> clList;
    String statusnya;

    public ListSearchAdapter(Context baseContext, List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> dataItems) {
        this.clList = dataItems;
        this.mContext = baseContext;
        this.requests = dataItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title_barang;
        public ImageView tv_barang_picture;
        public TextView tv_point;
        public LinearLayout card;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_title_barang = itemView.findViewById(R.id.title_barang);
            this.tv_point = itemView.findViewById(R.id.point);
            this.tv_barang_picture = itemView.findViewById(R.id.barang_picture);
            this.card = itemView.findViewById(R.id.card_search);
        }
    }

    @NonNull
    @Override
    public ListSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_searchresult, null);
        return new ListSearchAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSearchAdapter.MyViewHolder holder, int position) {
        final com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum cl = clList.get(position);

        holder.tv_title_barang.setText(cl.getAttributes().getNama());
        holder.tv_point.setText(String.valueOf(cl.getAttributes().getPoint()) + "POINT");
        Glide.with(mContext).load(cl.getAttributes().getFoto()).into(holder.tv_barang_picture);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailProductActivity.class);
                intent.putExtra("ID", cl.getId());
                intent.putExtra("IMAGE", cl.getAttributes().getFoto());
                intent.putExtra("TITLE", cl.getAttributes().getNama());
                intent.putExtra("DETAIL", cl.getAttributes().getDeskripsi());
                intent.putExtra("POINT_PRODUCT", cl.getAttributes().getPoint());
                intent.putExtra("POINT_REWARD", BusinesRewardActivity.point_reward);
                intent.putExtra("KTP", BusinesRewardActivity.ktpnpwp);
                intent.putExtra("NOKTP", BusinesRewardActivity.no_ktp);
                intent.putExtra("NONPWP", BusinesRewardActivity.no_npwp);
                view.getContext().startActivity(intent);
                view.getContext().startActivity(intent);
            }
        });

//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public int getItemCount() {
        if(clList == null ) {
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
                    List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> filteredList = new ArrayList<>();
                    for (com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum row : requests) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
//                        if (row.getStatus().toLowerCase().contains(charString.toLowerCase()) || row.getBranch().toLowerCase().contains(charString.toLowerCase()) || row.getClient_name().toLowerCase().contains(charString.toLowerCase()) || row.getTrackingId().toString().toLowerCase().contains(charString.toLowerCase()) || row.getProgram().toLowerCase().contains(charString.toLowerCase()) || row.getCreatedAt().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(row);
//                        }
                    }

                    clList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = clList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clList = (ArrayList<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ListClaimRewardAdapterListener {
        void onDataSelected(com.dicicilaja.app.API.Model.Request.Datum datum);
    }

    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes, final String claim) {
        Log.d("REQUEST TC:::", "Transaction ID " + transaction_id);
        Log.d("REQUEST TC:::", "Assigned ID " + assigned_id);
//        Call<ResRequestProcess> call = claimProcess.assign(apiKey,transaction_id, assigned_id, notes, claim);
//        call.enqueue(new Callback<ResRequestProcess>() {
//            @Override
//            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResRequestProcess> call, Throwable t) {
//
//            }
//        });
    }

    public void refreshAdapter(List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> data) {
        this.clList.addAll(data);
        notifyItemRangeChanged(0, this.clList.size());
    }
}
