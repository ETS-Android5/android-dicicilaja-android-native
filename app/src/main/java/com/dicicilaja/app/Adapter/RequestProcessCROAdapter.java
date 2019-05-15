package com.dicicilaja.app.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.Fragment.SummaryCRHFragment;
import com.dicicilaja.app.Fragment.TaskCROFragment;

/**
 * Created by ziterz on 1/29/2018.
 */

public class RequestProcessCROAdapter extends FragmentStatePagerAdapter {
    int num;

    public RequestProcessCROAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SummaryCRHFragment();

            case 1:
                return new TaskCROFragment();

            default:
                return new SummaryCRHFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
