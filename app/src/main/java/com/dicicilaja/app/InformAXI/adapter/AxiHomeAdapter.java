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
import com.dicicilaja.app.InformAXI.ui.profile.ProfileActivity;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.util.List;

import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_ID;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_NAME;

/**
 * Created by Husni with ❤
 */

public class AxiHomeAdapter extends RecyclerView.Adapter<AxiHomeAdapter.ViewHolder> {

    private List<AxiHome.DataBean> axiHomeList;
    private Context mContext;

    public AxiHomeAdapter(List<AxiHome.DataBean> axiHomeList, Context mContext) {
        this.axiHomeList = axiHomeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_axi_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AxiHome.DataBean o = axiHomeList.get(position);
        holder.bind(o);
    }

    @Override
    public int getItemCount() {
        return axiHomeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvNumber, tvDate;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            tvName = view.findViewById(R.id.tv_name);
            tvNumber = view.findViewById(R.id.tv_number);
            tvDate = view.findViewById(R.id.tv_date);
        }

        public void bind(final AxiHome.DataBean data) {
            tvName.setText(data.getNama());
            tvNumber.setText(data.getNomorAxiId());
            tvDate.setText(Tools.formatDate(data.getTanggalDaftar()));

            view.setOnClickListener(view -> {
                Intent i = new Intent(mContext, ProfileActivity.class);
                i.putExtra(PROFILE_NAME, data.getNama());
                i.putExtra(PROFILE_ID, data.getId());
                mContext.startActivity(i);
            });
        }
    }
}
