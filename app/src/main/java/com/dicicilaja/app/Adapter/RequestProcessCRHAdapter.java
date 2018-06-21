package com.dicicilaja.app.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.SummaryTCFragment;
import com.dicicilaja.app.Fragment.TaskCRHFragment;

/**
 * Created by ziterz on 1/28/2018.
 */

public class RequestProcessCRHAdapter extends FragmentStatePagerAdapter {
    int num;

    public RequestProcessCRHAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SummaryTCFragment();

            case 1:
                return new TaskCRHFragment();

            default:
                return new SummaryTCFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
