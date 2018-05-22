package com.dicicilaja.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.dicicilaja.Fragment.AkunFragment;
import com.dicicilaja.dicicilaja.Fragment.BantuanFragment;
import com.dicicilaja.dicicilaja.Fragment.BerandaFragment;
import com.dicicilaja.dicicilaja.Fragment.PengajuanFragment;

/**
 * Created by ziterz on 2/13/2018.
 */

public class MarketplacePagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public MarketplacePagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BerandaFragment();

            case 1:
                return new PengajuanFragment();

            case 2:
                return new BantuanFragment();

            case 3:
                return new AkunFragment();

            default:
                return new BerandaFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
