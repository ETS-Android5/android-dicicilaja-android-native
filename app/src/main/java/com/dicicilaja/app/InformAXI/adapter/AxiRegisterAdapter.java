package com.dicicilaja.app.InformAXI.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.InformAXI.model.AxiRegister;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class AxiRegisterAdapter extends RecyclerView.Adapter<AxiRegisterAdapter.ViewHolder> {

    private List<AxiRegister.DataBean> regList;
    private Context mContext;

    public AxiRegisterAdapter(List<AxiRegister.DataBean> regList, Context mContext) {
        this.regList = regList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_register, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AxiRegister.DataBean reg = regList.get(position);
        holder.bind(reg);
    }

    @Override
    public int getItemCount() {
        return regList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View v;
        TextView tvMonth;
        TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            v = itemView;
            tvMonth = v.findViewById(R.id.tv_month);
            tvCount = v.findViewById(R.id.tv_count);
        }

        public void bind(AxiRegister.DataBean reg) {
            tvMonth.setText(Tools.formatMount(reg.getId()));
            tvCount.setText(String.valueOf(reg.getCount()));
        }
    }
}
