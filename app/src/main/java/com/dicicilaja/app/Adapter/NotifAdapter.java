package com.dicicilaja.app.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.Inbox.Data.Notif.Datum;
import com.dicicilaja.app.R;

/**
 * Created by ziterz on 2/8/2018.
 */

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifViewHolder> {

    private List<Datum> notifs;
    private int rowLayout;
    private Context context;

    public static class NotifViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_notif;
        TextView resi;
        TextView tanggal;
        TextView status_notif;
        TextView detail_resi;

        public NotifViewHolder(View v) {
            super(v);
            card_notif  = v.findViewById(R.id.card_notif);
            resi            = v.findViewById(R.id.resi);
            tanggal         = v.findViewById(R.id.tanggal);
            status_notif          = v.findViewById(R.id.status_notif);
            detail_resi     = v.findViewById(R.id.detail_resi);
        }
    }

    public NotifAdapter(List<com.dicicilaja.app.API.Model.Notification.Datum> notifs, int rowLayout, Context context) {
        this.notifs = notifs;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NotifAdapter.NotifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NotifAdapter.NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotifAdapter.NotifViewHolder holder, int position) {
        holder.resi.setText("#" + notifs.get(position).getTrackingId().toString());
        holder.tanggal.setText(notifs.get(position).getTanggal());
        holder.status_notif.setText(notifs.get(position).getStatus());
        holder.detail_resi.setText(notifs.get(position).getDetail());
//


    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }
}
