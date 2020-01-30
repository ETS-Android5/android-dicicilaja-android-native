package com.dicicilaja.app.InformAXI.ui.profile;


import com.dicicilaja.app.InformAXI.model.AxiProfile;

/**
 * Created by Husni with ❤
 */

public class ProfileSingleton {

    private static final ProfileSingleton ourInstance = new ProfileSingleton();

    public static ProfileSingleton getInstance() {
        return ourInstance;
    }

    private ProfileSingleton() {
    }

    AxiProfile.DataBean.ProfileBean profile;

    /* InformAxi */
    AxiProfile.DataBean.BenefitBean benefit;

    AxiProfile.DataBean.LainnyaBean other;

    public AxiProfile.DataBean.ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(AxiProfile.DataBean.ProfileBean profile) {
        this.profile = profile;
    }

    public AxiProfile.DataBean.BenefitBean getBenefit() {
        return benefit;
    }

    public void setBenefit(AxiProfile.DataBean.BenefitBean benefit) {
        this.benefit = benefit;
    }

    public AxiProfile.DataBean.LainnyaBean getOther() {
        return other;
    }

    public void setOther(AxiProfile.DataBean.LainnyaBean other) {
        this.other = other;
    }

}
