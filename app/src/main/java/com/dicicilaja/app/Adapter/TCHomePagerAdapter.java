package com.dicicilaja.app.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.CompleteFragment;
import com.dicicilaja.app.Fragment.InprogressFragment;

/**
 * Created by daniellindp on 30/12/2017.
 */

public class TCHomePagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public TCHomePagerAdapter(FragmentManager fm, int num) {
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
