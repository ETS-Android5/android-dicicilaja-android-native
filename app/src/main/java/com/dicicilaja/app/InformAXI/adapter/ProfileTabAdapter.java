package com.dicicilaja.app.InformAXI.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dicicilaja.app.InformAXI.ui.profile.ProfileCallback;
import com.dicicilaja.app.InformAXI.ui.profile.benefit.BenefitFragment;
import com.dicicilaja.app.InformAXI.ui.profile.other.OtherFragment;
import com.dicicilaja.app.InformAXI.ui.profile.profile.ProfileFragment;

import java.util.List;

import static com.dicicilaja.app.InformAXI.utils.Constants.BENEFIT;
import static com.dicicilaja.app.InformAXI.utils.Constants.OTHER;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE;

/**
 * Created by Husni with ❤
 */

public class ProfileTabAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    private List<String> tabList;

    private int mCurrentPosition = -1;
    private int updateData = 0;

    public ProfileTabAdapter(@NonNull FragmentManager fm, List<String> tabList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager = fm;
        this.tabList = tabList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (tabList.get(position)) {
            case PROFILE:
                return ProfileFragment.newInstance(tabList.get(position));
            case BENEFIT:
                return BenefitFragment.newInstance(tabList.get(position));
            case OTHER:
                return OtherFragment.newInstance(tabList.get(position));
            default:
                return ProfileFragment.newInstance(tabList.get(position));
        }
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (object instanceof ProfileCallback) {
            ((ProfileCallback) object).update();
        }
        return super.getItemPosition(object);
    }

    public void update(int updateData) {
        this.updateData = updateData;
        notifyDataSetChanged();
    }
}
