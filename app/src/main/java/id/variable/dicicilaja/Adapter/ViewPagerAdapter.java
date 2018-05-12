package id.variable.dicicilaja.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.AkunFragment;
import id.variable.dicicilaja.Fragment.BantuanFragment;
import id.variable.dicicilaja.Fragment.BerandaFragment;
import id.variable.dicicilaja.Fragment.PengajuanFragment;

/**
 * Created by fawazrifqi on 12/05/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int num;

    public ViewPagerAdapter(FragmentManager fm, int num) {
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
