package id.variable.dicicilaja.Adapter;

/**
 * Created by fawazrifqi on 27/02/18.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.variable.dicicilaja.Content.PartnerModel;
import id.variable.dicicilaja.Content.RekomendasiModel;
import id.variable.dicicilaja.R;

public class ListPartnerAdapter extends RecyclerView.Adapter<ListPartnerAdapter.SingleItemRowHolder> {
    private ArrayList<PartnerModel> itemModels;
    private Context mContext;

    public ListPartnerAdapter(ArrayList<PartnerModel> itemModels, Context mContext) {
        this.itemModels = itemModels;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_partner, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        PartnerModel itemModel = itemModels.get(position);
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView discount_image;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
