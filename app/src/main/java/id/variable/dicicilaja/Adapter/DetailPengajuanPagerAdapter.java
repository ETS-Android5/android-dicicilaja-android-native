package id.variable.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.CompleteFragment;
import id.variable.dicicilaja.Fragment.InprogressFragment;
import id.variable.dicicilaja.Fragment.NasabahPemohonFragment;
import id.variable.dicicilaja.Fragment.PengajuanJaminanFragment;
import id.variable.dicicilaja.Fragment.RiwayatPengajuanFragment;

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

            default:
                return new RiwayatPengajuanFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
