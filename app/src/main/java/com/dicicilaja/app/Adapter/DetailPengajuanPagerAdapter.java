package com.dicicilaja.app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.NasabahPemohonFragment;
import com.dicicilaja.app.Fragment.PengajuanJaminanFragment;
import com.dicicilaja.app.Fragment.RiwayatPengajuanFragment;

/**
 * Created by ziterz on 30/12/2017.
 */

public class DetailPengajuanPagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public DetailPengajuanPagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PengajuanJaminanFragment();

            case 1:
                return new NasabahPemohonFragment();

            case 2:
                return new RiwayatPengajuanFragment();

            default:
                return new PengajuanJaminanFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
