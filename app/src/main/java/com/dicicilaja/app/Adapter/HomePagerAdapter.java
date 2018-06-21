package com.dicicilaja.app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.CompleteFragment;
import com.dicicilaja.app.Fragment.InprogressFragment;

/**
 * Created by ziterz on 9/7/2017.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public HomePagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InprogressFragment();

            case 1:
                return new CompleteFragment();

            default:
                return new InprogressFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
