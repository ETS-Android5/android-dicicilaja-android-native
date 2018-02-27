package id.variable.dicicilaja.Adapter;

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

import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.R;

/**
 * Created by ziterz on 2/17/2018.
 */

public class ListPromoAdapter extends RecyclerView.Adapter<ListPromoAdapter.SingleItemRowHolder> {
    private ArrayList<PromoModel> itemModels;
    private Context mContext;

    public ListPromoAdapter(ArrayList<PromoModel> itemModels, Context mContext) {
        this.itemModels = itemModels;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_promo, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v);
        Typeface opensans_extrabold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        TextView tv_title = v.findViewById(R.id.tv_title);
        tv_title.setTypeface(opensans_semibold);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        PromoModel itemModel = itemModels.get(position);
        holder.tv_title.setText(itemModel.getTitle());
        holder.tv_mitra.setText(itemModel.getMitra());
        holder.tv_harga.setText(itemModel.getHarga());
        holder.tv_tenor.setText(itemModel.getTenor());
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected ImageView discount_image;
        protected TextView tv_mitra;
        protected TextView tv_harga;
        protected TextView tv_tenor;



        public SingleItemRowHolder(View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_mitra = itemView.findViewById(R.id.tv_mitra);
            this.tv_harga = itemView.findViewById(R.id.tv_harga);
            this.tv_tenor = itemView.findViewById(R.id.tv_tenor);
            this.discount_image = itemView.findViewById(R.id.discount_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), tv_title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
