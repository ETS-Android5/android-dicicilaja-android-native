package com.dicicilaja.app.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemFavorite.Datum;
import com.dicicilaja.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAllAdapter extends RecyclerView.Adapter<FavoriteAllAdapter.RequestViewHolder> {
    private List<Datum> program;
    private int rowLayout;
    private Context context;

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card_program;
        ImageView image_program;
        TextView title_program;
        TextView value_harga;

        public RequestViewHolder(View v) {
            super(v);
            card_program = v.findViewById(R.id.card_pengajuan);
            image_program = v.findViewById(R.id.image_program);
            title_program = v.findViewById(R.id.title_program);
            value_harga = v.findViewById(R.id.value_harga);
        }
    }

    public FavoriteAllAdapter(List<Datum> program, int rowLayout, Context context) {
        this.program    = program;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public FavoriteAllAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FavoriteAllAdapter.RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAllAdapter.RequestViewHolder holder, int position) {
        holder.title_program.setText(program.get(position).getTitleProgram());
        holder.value_harga.setText(program.get(position).getPrice());
        Picasso.get().load(program.get(position).getImageUrl()).into(holder.image_program);

    }

    @Override
    public int getItemCount() {
        if(program.size() > 4){
            return 5;
        }else{
            return program.size();
        }
    }
}
