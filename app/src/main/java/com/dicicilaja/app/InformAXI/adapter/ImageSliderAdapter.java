package com.dicicilaja.app.InformAXI.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dicicilaja.app.InformAXI.model.Image;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class ImageSliderAdapter extends PagerAdapter {

    private List<Image> imageList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public ImageSliderAdapter(List<Image> imageList, Context mContext) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Image o = imageList.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_slider, container, false);

        ImageView image = v.findViewById(R.id.image);
        Tools.displayImage(mContext, o.getImageDrawable(), image);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, o);
            }
        });

        ((ViewPager) container).addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    /* Interface Callback */
    private interface OnItemClickListener {
        void onItemClick(View view, Image obj);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}