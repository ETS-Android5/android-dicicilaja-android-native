package com.dicicilaja.dicicilaja.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dicicilaja.dicicilaja.Fragment.AkunFragment;
import com.dicicilaja.dicicilaja.Fragment.BantuanFragment;
import com.dicicilaja.dicicilaja.Fragment.BerandaFragment;
import com.dicicilaja.dicicilaja.Fragment.NotUserFragment;
import com.dicicilaja.dicicilaja.Fragment.PengajuanFragment;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import static java.lang.Boolean.FALSE;

/**
 * Created by fawazrifqi on 12/05/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int num;
    SessionManager session;

    public ViewPagerAdapter(FragmentManager fm, int num, Context context) {
        super(fm);
        this.num = num;
        session = new SessionManager(context);
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
                if(session.isLoggedIn() == FALSE) {
                    return new NotUserFragment();
                }else{
                    return new AkunFragment();
                }


            default:
                return new BerandaFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }

}
