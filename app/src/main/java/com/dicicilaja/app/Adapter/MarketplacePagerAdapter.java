package com.dicicilaja.app.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.AkunFragment;
import com.dicicilaja.app.Fragment.BantuanFragment;
import com.dicicilaja.app.Fragment.BerandaFragment;
import com.dicicilaja.app.Fragment.PengajuanFragment;

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
