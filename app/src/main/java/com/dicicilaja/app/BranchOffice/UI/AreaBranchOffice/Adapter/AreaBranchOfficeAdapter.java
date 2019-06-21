package com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dicicilaja.app.BranchOffice.Data.AreaBranchOffice.DataItem;
import com.dicicilaja.app.R;

import java.util.List;

public class AreaBranchOfficeAdapter extends RecyclerView.Adapter<AreaBranchOfficeAdapter.AreaBranchViewHolder> {

    private List<DataItem> dataItems;
    private Context context;

    public static class AreaBranchViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout card_branch;
        TextView title_area;

        public AreaBranchViewHolder(View v) {
            super(v);
            card_branch = v.findViewById(R.id.card_branch);
            title_area = v.findViewById(R.id.title_area);
        }
    }

    public AreaBranchOfficeAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AreaBranchOfficeAdapter.AreaBranchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_branch_office, parent, false);
        return new AreaBranchOfficeAdapter.AreaBranchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaBranchOfficeAdapter.AreaBranchViewHolder holder, int position) {
        holder.title_area.setText(dataItems.get(position).getAttributes().getRegion());
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
}
