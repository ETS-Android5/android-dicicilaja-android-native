package com.dicicilaja.app.Inbox.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Inbox.Data.Notif.Datum;
import com.dicicilaja.app.R;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    private List<Datum> notifs;
    private int rowLayout;
    private Context context;

    public static class InboxViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_notif;
        ImageView icon_category;
        TextView notif_title;
        TextView notif_message;
        TextView notif_date;

        public InboxViewHolder(View v) {
            super(v);
            card_notif      = v.findViewById(R.id.card_notif);
            icon_category   = v.findViewById(R.id.icon_category);
            notif_title     = v.findViewById(R.id.notif_title);
            notif_message   = v.findViewById(R.id.notif_message);
            notif_date      = v.findViewById(R.id.notif_date);
        }
    }

    public InboxAdapter(List<Datum> notifs, int rowLayout, Context context) {
        this.notifs = notifs;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public InboxAdapter.InboxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new InboxAdapter.InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InboxAdapter.InboxViewHolder holder, int position) {
        switch (notifs.get(position).getAttributes().getCategory()) {
            case "Message":
                Glide.with(context).load(R.drawable.notif_message).centerCrop().into(holder.icon_category);
                break;
            case "Registration":
                Glide.with(context).load(R.drawable.notif_registration).centerCrop().into(holder.icon_category);
                break;
            case "Point Rewards":
                Glide.with(context).load(R.drawable.notif_point_rewards).centerCrop().into(holder.icon_category);
                break;
            case "Point Trip":
                Glide.with(context).load(R.drawable.notif_point_trip).centerCrop().into(holder.icon_category);
                break;
            case "Info AXI":
                Glide.with(context).load(R.drawable.notif_info_axi).centerCrop().into(holder.icon_category);
                break;
            case "Gathering":
                Glide.with(context).load(R.drawable.notif_gathering).centerCrop().into(holder.icon_category);
                break;
            case "Anual Fee":
                Glide.with(context).load(R.drawable.notif_anual_fee).centerCrop().into(holder.icon_category);
                break;
        }

        String value_title = notifs.get(position).getAttributes().getTitle();
        if (value_title.length() >= 35) {
            value_title = value_title.substring(0, 36) + "...";
            holder.notif_title.setText(value_title);
        } else {
            holder.notif_title.setText(value_title);
        }

        String value_message = notifs.get(position).getAttributes().getMessage();
        if (value_message.length() >= 35) {
            value_message = value_message.substring(0, 36) + "...";
            holder.notif_message.setText(value_message);
        } else {
            holder.notif_message.setText(value_message);
        }

        holder.notif_date.setText(notifs.get(position).getCreatedAtReadable());
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }
}
