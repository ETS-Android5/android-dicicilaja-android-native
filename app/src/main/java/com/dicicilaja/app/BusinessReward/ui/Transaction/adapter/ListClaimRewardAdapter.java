package com.dicicilaja.app.BusinessReward.ui.Transaction.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.DetailTransactionActivity;
import com.dicicilaja.app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListClaimRewardAdapter extends RecyclerView.Adapter<ListClaimRewardAdapter.MyViewHolder> {

    Context mContext;
    List<Datum> clList;
    String statusnya;

    public ListClaimRewardAdapter(List<Datum> dataItems, Context baseContext) {
        this.clList = dataItems;
        this.mContext = baseContext;
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

        holder.tv_tgl.setText(cl.getAttributes().getCreatedAt());

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<DetailProduk> call = apiService.getDetailProduk(cl.getAttributes().getProductCatalogId());
        call.enqueue(new Callback<DetailProduk>() {
            @Override
            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                String curString = cl.getAttributes().getCreatedAt();
                String[] separated = curString.split("T");
                String tgl = separated[0];

                String[] separated2 = tgl.split("-");
                String bulan = separated2[1];
                String tanggal = separated2[2];

                String finalBulan = null;

                if(bulan.equals("01")){
                    finalBulan = "Jan";
                }else if(bulan.equals("02")){
                    finalBulan = "Feb";
                }else if(bulan.equals("03")){
                    finalBulan = "Mar";
                }else if(bulan.equals("04")){
                    finalBulan = "Apr";
                }else if(bulan.equals("05")){
                    finalBulan = "Mei";
                }else if(bulan.equals("06")){
                    finalBulan = "Jun";
                }else if(bulan.equals("07")){
                    finalBulan = "Juli";
                }else if(bulan.equals("08")){
                    finalBulan = "Agus";
                }else if(bulan.equals("09")){
                    finalBulan = "Sep";
                }else if(bulan.equals("10")){
                    finalBulan = "Okt";
                }else if(bulan.equals("11")){
                    finalBulan = "Nov";
                }else if(bulan.equals("12")){
                    finalBulan = "Des";
                }

                holder.tv_tgl.setText(tanggal+" "+finalBulan);
                holder.tv_point.setText(response.body().getData().getAttributes().getPoint()+" POINT");
                holder.tv_merk.setText(String.valueOf(response.body().getData().getAttributes().getNama()));
                int status_id = cl.getAttributes().getStatusId();

                switch(status_id) {
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
                        Log.d("IDNYAAA", String.valueOf(cl.getId()));
                        intent.putExtra("ID", String.valueOf(cl.getId()));
                        view.getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<DetailProduk> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return clList.size();
    }
}
