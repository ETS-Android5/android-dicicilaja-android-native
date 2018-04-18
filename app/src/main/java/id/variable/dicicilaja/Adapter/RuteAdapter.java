package id.variable.dicicilaja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.API.Item.Request.Datum;
import id.variable.dicicilaja.Content.PartnerModel;
import id.variable.dicicilaja.Content.RuteModel;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 13/04/18.
 */

public class RuteAdapter extends RecyclerView.Adapter<RuteAdapter.RequestViewHolder>  {
    private List<RuteModel> rutes;
    private int rowLayout;
    private Context context;

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView judul;
        TextView deskripsi;

        public RequestViewHolder(View v) {
            super(v);
            day  = v.findViewById(R.id.day);
            judul            = v.findViewById(R.id.judul);
            deskripsi         = v.findViewById(R.id.deskripsi);
        }
    }

    public RuteAdapter(List<RuteModel> rute, int rowLayout, Context context) {
        this.rutes = rute;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RuteAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RuteAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RuteAdapter.RequestViewHolder holder, int position) {
        RuteModel rute = rutes.get(position);
        holder.judul.setText(rute.getTitle());
        holder.deskripsi.setText(rute.getDeskripsi());
        holder.day.setText(rute.getDay());
    }

    @Override
    public int getItemCount() {
        return rutes.size();
    }
}
