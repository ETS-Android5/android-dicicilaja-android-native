package id.variable.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.SummaryCRHFragment;
import id.variable.dicicilaja.Fragment.SummaryCROFragment;
import id.variable.dicicilaja.Fragment.TaskCRHFragment;
import id.variable.dicicilaja.Fragment.TaskCROFragment;

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
                return new SummaryCROFragment();

            case 1:
                return new TaskCROFragment();

            default:
                return new SummaryCROFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
