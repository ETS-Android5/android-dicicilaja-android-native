package com.dicicilaja.app.InformAXI.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.model.axiterdaftar.Datum;
import com.dicicilaja.app.InformAXI.ui.profile.ProfileActivity;
import com.dicicilaja.app.InformAXI.ui.register.DetailRegisterActivity;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.text.DecimalFormat;
import java.util.List;

import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_ID;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_NAME;

public class SmHomeAdapter extends RecyclerView.Adapter<SmHomeAdapter.ViewHolder> {

    private List<Datum> axiHomeList;
    private Context mContext;

    public SmHomeAdapter(List<Datum> axiHomeList, Context mContext) {
        this.axiHomeList = axiHomeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_sm_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum o = axiHomeList.get(position);
        holder.bind(o, position);
    }

    @Override
    public int getItemCount() {
        return axiHomeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvNumber, tvDate, tvOrder, tvStatus;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            tvName = view.findViewById(R.id.tv_name);
            tvStatus = view.findViewById(R.id.tv_status);
            tvDate = view.findViewById(R.id.tv_date);
        }

        public void bind(final Datum data, final int position) {

            DecimalFormat decim = new DecimalFormat("#,###.##");
            tvName.setText(data.getDate());
            tvStatus.setText(decim.format((data.getCount())).replace(",", "."));
//            tvNumber.setText(data.getNomorAxiId());
//            tvDate.setText(Tools.formatDate(data.getTanggalDaftar()));
//            tvOrder.setText("#" + decim.format((position+1)).replace(",", "."));

            view.setOnClickListener(v -> {
                mContext.startActivity(
                        new Intent(mContext, DetailRegisterActivity.class)
                                .putExtra("monthId", data.getId())
                                .putExtra("monthName", data.getDate())
                );
            });
        }
    }
}
