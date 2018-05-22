package com.dicicilaja.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.dicicilaja.Fragment.RegisterAxiFragment;
import com.dicicilaja.dicicilaja.Fragment.RegisterCustomerFragment;
import com.dicicilaja.dicicilaja.Fragment.RegisterMaxiFragment;

/**
 * Created by ziterz on 12/20/2017.
 */

public class RegisterAdapter extends FragmentStatePagerAdapter {
    int num;

    public RegisterAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegisterCustomerFragment();

            case 1:
                return new RegisterAxiFragment();

            case 2:
                return new RegisterMaxiFragment();

            default:
                return new RegisterCustomerFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
