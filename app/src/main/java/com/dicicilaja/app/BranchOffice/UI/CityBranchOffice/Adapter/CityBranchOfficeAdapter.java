package com.dicicilaja.app.BranchOffice.UI.CityBranchOffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dicicilaja.app.BranchOffice.Data.KotaBranchOffice.DataItem;
import com.dicicilaja.app.R;

import java.util.List;

public class CityBranchOfficeAdapter extends RecyclerView.Adapter<CityBranchOfficeAdapter.CityBranchViewHolder>  {

    private List<DataItem> dataItems;
    private Context context;

    public static class CityBranchViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout card_branch;
        TextView title_area;

        public CityBranchViewHolder(View v) {
            super(v);
            card_branch = v.findViewById(R.id.card_branch);
            title_area = v.findViewById(R.id.title_area);
        }
    }

    public CityBranchOfficeAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CityBranchOfficeAdapter.CityBranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_branch_office, parent, false);
        return new CityBranchOfficeAdapter.CityBranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityBranchOfficeAdapter.CityBranchViewHolder holder, int position) {
        holder.title_area.setText(dataItems.get(position).getAttributes().getKota());
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
}
