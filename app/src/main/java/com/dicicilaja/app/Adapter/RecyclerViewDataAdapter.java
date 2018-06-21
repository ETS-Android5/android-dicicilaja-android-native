package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

import com.dicicilaja.app.API.Item.Product.SectionDataModel;
import com.dicicilaja.app.R;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {
    private ArrayList<SectionDataModel> dataList;
    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private SnapHelper snapHelper;

    public RecyclerViewDataAdapter(ArrayList<SectionDataModel> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder rowHolder = new ItemRowHolder(v);
        snapHelper = new GravitySnapHelper(Gravity.START);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final String sectionName = dataList.get(position).getHeaderTitle();
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        SectionListDataAdapter adapter = new SectionListDataAdapter(singleSectionItems, mContext);
        Typeface opensans_extrabold = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Regular.ttf");

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        adapter.notifyDataSetChanged();
        holder.recyclerView.setAdapter(adapter);
        holder.title_program1.setText(sectionName);
        holder.title_program1.setTypeface(opensans_bold);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
        holder.show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Lihat semua kategori " + sectionName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        protected RecyclerView recyclerView;
        protected RelativeLayout show_all;
        protected TextView title_program1;

        public ItemRowHolder(View itemView) {
            super(itemView);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            this.show_all = itemView.findViewById(R.id.show_all);
            this.title_program1 = itemView.findViewById(R.id.title_program1);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
