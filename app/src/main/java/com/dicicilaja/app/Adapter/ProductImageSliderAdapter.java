package com.dicicilaja.app.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAsuransi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCustomerSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceEdukasi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceExtraguna;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceSehat;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceTravel;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUsaha;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.Datum;
import com.dicicilaja.app.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductImageSliderAdapter extends SliderViewAdapter<ProductImageSliderAdapter.SliderAdapterVH> {
    private Context context;
    String content;
    HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
    int maxSlide;

    public ProductImageSliderAdapter(Context context, int maxSlide, HashMap<Integer, String> file_maps ) {
        this.context = context;
        this.maxSlide = maxSlide;
        this.file_maps = file_maps;
    }

    @Override
    public ProductImageSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout_item, null);
        return new ProductImageSliderAdapter.SliderAdapterVH(inflate);



    }

    @Override
    public void onBindViewHolder(ProductImageSliderAdapter.SliderAdapterVH viewHolder, int position) {
//        viewHolder.textViewDescription.setText("This is slider item " + position);

        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(0))
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(1))
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(2))
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(3))
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(4))
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                Glide.with(viewHolder.itemView)
                        .load(file_maps.get(0))
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return maxSlide;
    }


    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
