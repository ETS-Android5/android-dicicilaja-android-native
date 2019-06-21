package com.dicicilaja.app.BranchOffice.UI.BranchOffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dicicilaja.app.BranchOffice.Data.BranchOffice.DataItem;
import com.dicicilaja.app.R;

import java.util.List;

public class BranchOfficeAdapter extends RecyclerView.Adapter<BranchOfficeAdapter.BranchOfficeViewHolder> {

    private List<DataItem> dataItems;
    private Context context;

    public static class BranchOfficeViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout card_branch;
        TextView branch_name;
        TextView address;
        TextView title_city;
        TextView title_district;

        public BranchOfficeViewHolder(View v) {
            super(v);
            card_branch = v.findViewById(R.id.card_branch);
            branch_name = v.findViewById(R.id.branch_name);
            address = v.findViewById(R.id.address);
            title_city = v.findViewById(R.id.title_city);
            title_district = v.findViewById(R.id.title_district);
        }
    }

    public BranchOfficeAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public BranchOfficeAdapter.BranchOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_branch, parent, false);
        return new BranchOfficeAdapter.BranchOfficeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchOfficeAdapter.BranchOfficeViewHolder holder, int position) {
        try {
            holder.branch_name.setText(dataItems.get(position).getAttributes().getNama());
            holder.address.setText(dataItems.get(position).getAttributes().getDetail().getAlamat());
            holder.title_city.setText(dataItems.get(position).getAttributes().getDetail().getKota());
            holder.title_district.setText(dataItems.get(position).getAttributes().getDetail().getKecamatan());
        } catch (Exception ex) { }
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
}
