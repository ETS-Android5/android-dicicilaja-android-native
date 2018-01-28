package id.variable.dicicilaja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.variable.dicicilaja.Fragment.NasabahPemohonFragment;
import id.variable.dicicilaja.Fragment.NotesRequestFragment;
import id.variable.dicicilaja.Fragment.PengajuanJaminanFragment;
import id.variable.dicicilaja.Fragment.RiwayatPengajuanFragment;
import id.variable.dicicilaja.Fragment.SummaryCRHFragment;
import id.variable.dicicilaja.Fragment.TaskCRHFragment;
import id.variable.dicicilaja.Fragment.TaskRequestFragment;

/**
 * Created by ziterz on 1/28/2018.
 */

public class RequestProcessAdapter extends FragmentStatePagerAdapter {
    int num;

    public RequestProcessAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SummaryCRHFragment();

            case 1:
                return new TaskCRHFragment();

            default:
                return new SummaryCRHFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
