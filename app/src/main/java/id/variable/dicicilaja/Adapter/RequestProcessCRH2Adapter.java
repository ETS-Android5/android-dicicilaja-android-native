package id.variable.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.SummaryCROFragment;
import id.variable.dicicilaja.Fragment.SummaryTCFragment;
import id.variable.dicicilaja.Fragment.TaskCRHFragment;

/**
 * Created by ziterz on 1/30/2018.
 */

public class RequestProcessCRH2Adapter extends FragmentStatePagerAdapter {
    int num;

    public RequestProcessCRH2Adapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SummaryCROFragment();

            case 1:
                return new TaskCRHFragment();

            default:
                return new SummaryCROFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
