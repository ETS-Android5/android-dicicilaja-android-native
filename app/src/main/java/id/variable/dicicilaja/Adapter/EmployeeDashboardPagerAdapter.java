package id.variable.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.CompleteFragment;
import id.variable.dicicilaja.Fragment.InprogressFragment;

/**
 * Created by daniellindp on 30/12/2017.
 */

public class EmployeeDashboardPagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public EmployeeDashboardPagerAdapter(FragmentManager fm, int num) {
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
